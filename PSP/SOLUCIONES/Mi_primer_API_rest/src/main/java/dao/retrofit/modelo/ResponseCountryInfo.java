package dao.retrofit.modelo;

import com.squareup.moshi.Json;
import common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCountryInfo {
    private int id;
    private String name;
    private String iso3;
    @Json(name = Constants.NUMERIC_CODE)
    private String numericCode;
    private String iso2;
    @Json(name = Constants.PHONECODE)
    private String phoneCode;
    private String capital;
    private String currency;
    @Json(name = Constants.CURRENCY_NAME_)
    private String currencyName;
    @Json(name = Constants.CURRENCY_SYMBOL)
    private String currencySymbol;
    @Json(name = Constants.TLD)
    private String topLevelDomain;
    @Json(name = Constants.NATIVE)
    private String nativeName;
    private String region;
    private String subregion;
    private String timezones;
    private String translations;
    private String latitude;
    private String longitude;
    private String emoji;
    private String emojiU;
}