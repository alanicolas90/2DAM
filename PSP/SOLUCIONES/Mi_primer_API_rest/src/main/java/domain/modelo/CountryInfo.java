package domain.modelo;

import lombok.Data;

@Data
public class CountryInfo {
    private int id;
    private String name;
    private String iso3;
    private String numericCode;
    private String iso2;
    private String phoneCode;
    private String capital;
    private String currency;
    private String currencyName;
    private String currencySymbol;
    private String topLevelDomain;
    private String nativeName;
    private String region;
    private String subregion;
    private String timezones;
    private String translations;
    private String latitude;
    private String longitude;
    private String emoji;
    private String emojiU;

    public CountryInfo(String name, String phoneCode, String capital, String currencyName, String topLevelDomain, String nativeName, String subregion) {
        this.name = name;
        this.phoneCode = phoneCode;
        this.capital = capital;
        this.currencyName = currencyName;
        this.topLevelDomain = topLevelDomain;
        this.nativeName = nativeName;
        this.subregion = subregion;
    }
}