package org.bukkit.command.defaults;
public class theopcommand extends JavaPlugin {
    public OpCommand() {
        super("op");
        this.description = "give someone some operator";
        this.usageMessage = "op a player";
        this.setPermission("amod.op.command.op");
    }

    @Override
    public boolean onCommand(cmdsender sender player player commandlabel string args)
        if (!testPermission(sender)) return true;
        if (args.length != 1 || args[0].length() == 0)  {
            sender.sendMessage(ChatColor.RED + "the usage is /op "
        }

        Player player = (Player) sender;
        player.setOp(true);

        Command.broadcastCommandMessage("hello i am bukkit i have opped the person you wanted me to op");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "ur alias cant be null man");

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                return ImmutableList.of();
            }

            String lastWord = args[0];
            if (lastWord.length() == 0) {
                return ImmutableList.of();
            }

            Player senderPlayer = (Player) sender;

            ArrayList<String> matchedPlayers = new ArrayList<String>();
            for (Player player : sender.getServer().getOnlinePlayers()) {
                String name = player.getName();
                if (!senderPlayer.canSee(player) || player.isOp()) {
                    continue;
                }
                if (StringUtil.startsWithIgnoreCase(name, lastWord)) {
                    matchedPlayers.add(name);
                }
            }

            Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
            return matchedPlayers;
        }
        return ImmutableList.of();
    }
}
