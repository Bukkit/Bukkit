package org.bukkit.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

public abstract class Command {
	/**
	 * Defines how the parameters will be parsed.
	 * @author Fabian Neundorf
	 */
	public enum Parsing {
		/** Don't split the parameters anyhow. */
		NONE,
		/** 
		 * Split the parameters at the spaces.
		 * 
		 * @see This method uses {@link String#split(String)}.
		 */
		SPLIT,
		/**
		 * This parsing option will split the parameters at the spaces.
		 * 
		 * @see This method uses {@link Command#parseLine(String)}.
		 */
		QUOTED;
		
		public static Parsing getParser(String name) {
			if (name.equalsIgnoreCase("none")) {
				return NONE;
			} else if (name.equalsIgnoreCase("quoted")) {
				return QUOTED;
			} else {
				return SPLIT;
			}
		}
	}
	
    private final String name;
    private List<String> aliases;
    protected String tooltip = "";
    protected String usageMessage;
    private Parsing parsing;

    public Command(String name) {
        this.name = name;
        this.aliases = new ArrayList<String>();
        this.usageMessage = "/" + name;  
        this.parsing = Parsing.SPLIT;
    }

    public boolean parse(CommandSender sender, String currentAlias, String args) {
    	switch (this.parsing) {
    	case NONE :
    		return this.execute(sender, currentAlias, new String[] {args});
    	case SPLIT :
    		return this.execute(sender, currentAlias, args.split(" "));
    	case QUOTED :
    		return this.execute(sender, currentAlias, Command.parseLine(args));
    	}
    	return false;
    }
    
    protected abstract boolean execute(CommandSender sender, String currentAlias, String[] args);

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getUsage() {
        return usageMessage;
    }

	public Parsing getParsing() {
		return parsing;
	}

    public Command setAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    public Command setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Command setUsage(String usage) {
        this.usageMessage = usage;
        return this;
    }

	public void setParsing(Parsing parsing) {
		this.parsing = parsing;
	}
	
	/**
	 * Parses a string line using quoting and escaping. It will split the line where a space is, but ignores quoted or escaped spaces.
	 *  
	 * Examples:
     * <blockquote><table>
     * <tr>
     *  <th>Input</th>
     *  <th>Output</th>
     * </tr>
     * <tr><td>Hello World</td>
     *     <td><tt>{ Hello, World }</tt></td></tr>
     * <tr><td>"Hello World"</td>
     *     <td><tt>{ Hello World }</tt></td></tr>
     * <tr><td>Hello\ World</td>
     *     <td><tt>{ Hello World }</tt></td></tr>
     * <tr><td>"Hello World \"Bukkit\""</td>
     *     <td><tt>{ Hello World "Bukkit" }</tt></td></tr>
     * </table></blockquote>
     * 
     * <b>Notice</b>: This method ignores illegal escapes.
	 * @param line
	 *            The command line.
	 * @return The parsed segments.
	 */
	public static String[] parseLine(String line) {
		boolean quoted = false;
		boolean escaped = false;
		int lastStart = 0;
		char[] word = new char[line.length()];
		int wordIndex = 0;
		List<String> values = new ArrayList<String>(2);
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (escaped) {
				word[wordIndex] = c;
				wordIndex++;
				escaped = false;
			} else {
				switch (c) {
				case '"':
					quoted = !quoted;
					break;
				case '\\':
					escaped = true;
					break;
				case ' ':
					if (!quoted) {
						if (lastStart < i) {
							values.add(String.copyValueOf(word, 0, wordIndex));
							word = new char[line.length() - i];
							wordIndex = 0;
						}
						lastStart = i + 1;
						break;
					}
				default:
					word[wordIndex] = c;
					wordIndex++;
					break;
				}
			}
		}
		if (wordIndex > 0) {
			values.add(String.copyValueOf(word, 0, wordIndex));
		}
		return values.toArray(new String[0]);
	}
}