package data.countries.cities;

import data.countries.CountryData;

public enum RussiaCitiesData implements ICity{

    PENZA("Пенза", CountryData.RUSSIA);

    private String name;
    private CountryData countryData;

    RussiaCitiesData(String name, CountryData countryData) {
        this.name = name;
        this.countryData = countryData;
    }

    public String getName() {
        return name;
    }

    public CountryData getCountryData() {
        return countryData;
    }
}