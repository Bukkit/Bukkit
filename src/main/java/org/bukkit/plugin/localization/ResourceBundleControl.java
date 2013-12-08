package org.bukkit.plugin.localization;

import org.apache.commons.lang.Validate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleControl extends ResourceBundle.Control {
    private File baseDir;

    public ResourceBundleControl(File baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public List<String> getFormats(String baseName) {
        Validate.notNull(baseName);
        return Collections.unmodifiableList(Arrays.asList("yml"));
    }


    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
        //Validate the Input
        Validate.notNull(baseName);
        Validate.notNull(locale);
        Validate.notNull(format);
        Validate.notNull(loader);

        //Build the correct resourceName
        String resourceName = toResourceName(toBundleName(baseName, locale), format);

        //Construct the File from the Filesystem
        File file = new File(baseDir, resourceName);

        InputStreamReader is = null;
        ResourceBundle bundle = null;

        try {
            //If the File is there load it
            System.out.println(file.getAbsoluteFile().toString());
            if (file.isFile()) {
                System.out.println("Reading from disk");
                is = new InputStreamReader(new FileInputStream(file), "UTF8");
            } else {
                //If the file is not on the Disk read it from the JAR
                if (reload) {
                    URL url = loader.getResource("lang/" + resourceName);
                    if (url != null) {
                        URLConnection connection = url.openConnection();
                        if (connection != null) {
                            connection.setUseCaches(false);
                            is = new InputStreamReader(connection.getInputStream(), "UTF8");
                        }
                    }
                } else {
                    is = new InputStreamReader(loader.getResourceAsStream("lang/" + resourceName), "UTF8");
                }
            }

            if(is == null) return null;

            bundle = new YamlResourceBundle(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return bundle;
    }

    /**
     * If the plugin gets unloaded remove the refs
     */
    public void cleanup() {
        baseDir = null;
    }
}
