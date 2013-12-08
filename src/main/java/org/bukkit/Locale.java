package org.bukkit;

public enum Locale {
    //Sorts of english
    ENGLISH("English", "en_US"),
    BRITISH_ENGLISH("British English", "en_GB"),
    AUSTRALIAN_ENGLISH("Australian English", "en_AU"),
    CANADIAN_ENGLISH("Canadian English", "en_CA"),
    PIRATE_SPEAK("Pirate Speak", "en_PT"),

    //Sorts of French
    FRENCH("Français", "fr_FR"),
    CANADIAN_FRENCH("Français", "fr_CA"),

    //Sorts of spanish
    SPANISH("Español", "es_ES"),
    ARGENTINEAN_SPANISH("Español Argentino", "es_AR"),
    MEXICO_SPANISH("Español México", "es_MX"),
    URUGUAY_SPANISH("Español Uruguay", "es_UY"),
    VENEZUELA_SPANISH("Español Venezuela", "es_VE"),

    //Sorts of chinese
    SIMPLIFIED_CHINESE("简体中文", "zh_CN"),
    TRADITIONAL_CHINESE("繁體中文", "zh_TW"),

    //Sorts of PORTUGUESE
    PORTUGUESE("Português", "pt_PT"),
    BRAZIL_PORTUGUESE("Português", "pt_BR"),

    //Other unique locales
    AFRIKAANS("Afrikaans", "af_ZA"),
    ARABIC("العربية", "ar_SA"),
    BULGARIAN("Български", "bg_BG"),
    CATALAN("Català", "ca_ES"),
    CZECH("Čeština", "cs_CZ"),
    WELSH("Cymraeg", "cy_GB"),
    DANISH("Dansk", "da_DK"),
    GERMAN("Deutsch", "de_DE"),
    GREEK("Ελληνικά", "el_GR"),
    ESPERANTO("Esperanto", "eo_EO"),
    ESTONIAN("Eesti", "et_EE"),
    BASQUE("Euskara", "eu_ES"),
    PERSIAN("زبان انگلیسی", "fa_IR"),
    FINNISH("Suomi", "fi_FI"),
    FILIPINO("Tagalog", "fil_PH"),
    IRISH("Gaeilge", "ga_IE"),
    GALICIAN("Galego", "gl_ES"),
    HEBREW("עברית", "he_IL"),
    HINDI("अंग्रेज़ी", "hi_IN"),
    CROATIAN("Hrvatski", "hr_HR"),
    HUNGARIAN("Magyar", "hu_HU"),
    ARMENIAN("Հայերեն", "hy_AM"),
    INDONESIAN("Bahasa Indonesia", "id_ID"),
    ICELANDIC("Íslenska", "is_IS"),
    ITALIAN("Italiano", "it_IT"),
    JAPANESE("日本語", "ja_JP"),
    GEORGIAN("ქართული", "ka_GE"),
    KOREAN("한국어", "ko_KR"),
    CORNISH("Kernewek", "kw_GB"),
    KIRGHIZ("अंग्रेज़ी", "ky_KG"),
    LUXEMBOURGISH("Lëtzebuergesch", "lb_LU"),
    LITHUANIAN("Lietuvių", "lt_LT"),
    LATVIAN("Latviešu", "lv_LV"),
    MAORI("Bahasa Melayu", "mi_NZ"),
    MALAY("Bahasa Melayu", "ms_MY"),
    MALTI("Malti", "mt_MT"),
    NORWEGIAN_BOKMAL("Norsk", "nb_NO"),
    DUTCH("Nederlands", "nl_NL"),
    NORWEGIAN_NYNORSK("Norsk nynorsk", "nn_NO"),
    NORWEGIAN("Norsk", "no_NO"),
    OCCITAN("Occitan", "oc_FR"),
    ROMANIAN("Română", "ro_RO"),
    RUSSIAN("Русский", "ru_RU"),
    SLOVAK("Angličtina", "sk_SK"),
    SLOVENIAN("Slovenščina", "sl_SI"),
    SERBIAN("Српски", "sr_SP"),
    SWEDISH("Svenska", "sv_SE"),
    THAI("ภาษาไทย", "th_TH"),
    TURKISH("Türkçe", "tr_TR"),
    UKRAINIAN("Українська", "uk_UA"),
    VIETNAMESE("Tiếng Việt", "vi_VI"),
    POLISH("Polski", "pl_PL");

    private String name;
    private String code;

    private Locale(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Get the Langauge Name in the correct Locale
     *
     * @return Language Name translated into the correct Locale
     */
    public String getName() {
        return name;
    }

    /**
     * Get the ISO Code of this Locale
     *
     * @return ISO Code of this Locale
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the correct locale based on the ISO formatted String
     *
     * @param code The ISO formatted String. Like: en_US
     * @return Correct Locale Enum or null if not found
     */
    public static Locale getByCode(String code) {
        for (Locale l : values()) {
            if (l.getCode().equalsIgnoreCase(code)) return l;
        }

        return null;
    }

}
