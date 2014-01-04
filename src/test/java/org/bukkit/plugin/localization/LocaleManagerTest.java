package org.bukkit.plugin.localization;

import org.bukkit.TestCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocaleManagerTest {
    @Test(expected = IllegalArgumentException.class)
    public void testSetDefaultWithoutLoading() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToRegisterEmptyLoader() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(null);
    }

    @Test
    public void testRegisterInMemoryResourceLoader() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);

        assertThat(testPlugin.getLocaleManager().translate(TestCommandSender.getInstance(), "test"), is("This is a in Memory Test"));
    }

    @Test(expected = ResourceNotLoadedException.class)
    public void testTranslateKeyWhichIsNotLoaded() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);
        testPlugin.getLocaleManager().translate(TestCommandSender.getInstance(), "test1");
    }

    @Test
    public void testDefaultLocaleForPlayer() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);

        assertThat(testPlugin.getLocaleManager().translate(TestPlayer.getInstance(), "test"), is("This is a in Memory Test"));
    }

    @Test
    public void testPlayerLocale() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().load(new Locale("de", "DE"), "Dies ist ein RAM Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);

        assertThat(testPlugin.getLocaleManager().translate(TestPlayer.getInstance(), "test"), is("Dies ist ein RAM Test"));
    }

    @Test(expected = ResourceNotLoadedException.class)
    public void testPlayerLocaleNotLoadedKey() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().load(new Locale("de", "DE"), "Dies ist ein RAM Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);
        testPlugin.getLocaleManager().translate(TestPlayer.getInstance(), "test1");
    }

    @Test(expected = NullPointerException.class)
    public void testCleanup() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().load(new Locale("de", "DE"), "Dies ist ein RAM Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);
        testPlugin.getLocaleManager().cleanup();

        //A reload should throw a NullPointer since the ResourceManager has been cleared out
        testPlugin.getLocaleManager().reload();
    }

    @Test()
    public void testReload() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().load(new Locale("de", "DE"), "Dies ist ein RAM Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);
        testPlugin.getLocaleManager().reload();
    }

    @Test()
    public void testLocaleChange() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(Locale.US, "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().load(new Locale("de", "DE"), "Dies ist ein RAM Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(Locale.US);

        Player testPlayer = TestPlayer.getInstance();
        assertThat(testPlugin.getLocaleManager().translate(testPlayer, "test"), is("Dies ist ein RAM Test"));

        TestPlayer.setLocale("en_US");
        assertThat(testPlugin.getLocaleManager().translate(testPlayer, "test"), is("This is a in Memory Test"));

        TestPlayer.setLocale("cy_CZ");
        assertThat(testPlugin.getLocaleManager().translate(testPlayer, "test"), is("This is a in Memory Test"));
    }

    @Test()
    public void testInheritLocale() throws Exception {
        Plugin testPlugin = LocaleTestPlugin.getInstance();
        testPlugin.getLocaleManager().registerLoader(new InMemoryResourceLoader());
        testPlugin.getLocaleManager().load(new Locale("en"), "This is a in Memory Test:inmemory");
        testPlugin.getLocaleManager().setDefaultLocale(new Locale("en"));

        Player testPlayer = TestPlayer.getInstance();
        TestPlayer.setLocale("en_US");
        assertThat(testPlugin.getLocaleManager().translate(testPlayer, "test"), is("This is a in Memory Test"));

        testPlugin.getLocaleManager().load(new Locale("en", "US"), "enUS:inmemory");
        assertThat(testPlugin.getLocaleManager().translate(testPlayer, "test"), is("enUS"));
    }
}
