package model;

import java.util.ArrayList;
import java.util.List;


        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;






 class Datum {

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("precipIntensity")
    @Expose
    private Double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;
    @SerializedName("precipType")
    @Expose
    private String precipType;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    @SerializedName("apparentTemperature")
    @Expose
    private Double apparentTemperature;
    @SerializedName("dewPoint")
    @Expose
    private Double dewPoint;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("windSpeed")
    @Expose
    private Double windSpeed;
    @SerializedName("windBearing")
    @Expose
    private Integer windBearing;
    @SerializedName("visibility")
    @Expose
    private Double visibility;
    @SerializedName("cloudCover")
    @Expose
    private Double cloudCover;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("ozone")
    @Expose
    private Double ozone;

    /**
     *
     * @return
     * The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     *
     * @return
     * The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     * The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The precipIntensity
     */
    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    /**
     *
     * @param precipIntensity
     * The precipIntensity
     */
    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    /**
     *
     * @return
     * The precipProbability
     */
    public Double getPrecipProbability() {
        return precipProbability;
    }

    /**
     *
     * @param precipProbability
     * The precipProbability
     */
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    /**
     *
     * @return
     * The precipType
     */
    public String getPrecipType() {
        return precipType;
    }

    /**
     *
     * @param precipType
     * The precipType
     */
    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    /**
     *
     * @return
     * The temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     *
     * @param temperature
     * The temperature
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     *
     * @return
     * The apparentTemperature
     */
    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    /**
     *
     * @param apparentTemperature
     * The apparentTemperature
     */
    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    /**
     *
     * @return
     * The dewPoint
     */
    public Double getDewPoint() {
        return dewPoint;
    }

    /**
     *
     * @param dewPoint
     * The dewPoint
     */
    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     *
     * @return
     * The humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     * The windSpeed
     */
    public Double getWindSpeed() {
        return windSpeed;
    }

    /**
     *
     * @param windSpeed
     * The windSpeed
     */
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     *
     * @return
     * The windBearing
     */
    public Integer getWindBearing() {
        return windBearing;
    }

    /**
     *
     * @param windBearing
     * The windBearing
     */
    public void setWindBearing(Integer windBearing) {
        this.windBearing = windBearing;
    }

    /**
     *
     * @return
     * The visibility
     */
    public Double getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility
     * The visibility
     */
    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    /**
     *
     * @return
     * The cloudCover
     */
    public Double getCloudCover() {
        return cloudCover;
    }

    /**
     *
     * @param cloudCover
     * The cloudCover
     */
    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    /**
     *
     * @return
     * The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     *
     * @param pressure
     * The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     *
     * @return
     * The ozone
     */
    public Double getOzone() {
        return ozone;
    }

    /**
     *
     * @param ozone
     * The ozone
     */
    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

}


public class WeatherApi {
    public class Currently {

        @SerializedName("time")
        @Expose
        private Integer time;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("precipIntensity")
        @Expose
        private Double precipIntensity;
        @SerializedName("precipProbability")
        @Expose
        private Double precipProbability;
        @SerializedName("precipType")
        @Expose
        private String precipType;
        @SerializedName("temperature")
        @Expose
        private Double temperature;
        @SerializedName("apparentTemperature")
        @Expose
        private Double apparentTemperature;
        @SerializedName("dewPoint")
        @Expose
        private Double dewPoint;
        @SerializedName("humidity")
        @Expose
        private Double humidity;
        @SerializedName("windSpeed")
        @Expose
        private Double windSpeed;
        @SerializedName("windBearing")
        @Expose
        private Integer windBearing;
        @SerializedName("visibility")
        @Expose
        private Double visibility;
        @SerializedName("cloudCover")
        @Expose
        private Double cloudCover;
        @SerializedName("pressure")
        @Expose
        private Double pressure;
        @SerializedName("ozone")
        @Expose
        private Double ozone;

        /**
         *
         * @return
         * The time
         */
        public Integer getTime() {
            return time;
        }

        /**
         *
         * @param time
         * The time
         */
        public void setTime(Integer time) {
            this.time = time;
        }

        /**
         *
         * @return
         * The summary
         */
        public String getSummary() {
            return summary;
        }

        /**
         *
         * @param summary
         * The summary
         */
        public void setSummary(String summary) {
            this.summary = summary;
        }

        /**
         *
         * @return
         * The icon
         */
        public String getIcon() {
            return icon;
        }

        /**
         *
         * @param icon
         * The icon
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }

        /**
         *
         * @return
         * The precipIntensity
         */
        public Double getPrecipIntensity() {
            return precipIntensity;
        }

        /**
         *
         * @param precipIntensity
         * The precipIntensity
         */
        public void setPrecipIntensity(Double precipIntensity) {
            this.precipIntensity = precipIntensity;
        }

        /**
         *
         * @return
         * The precipProbability
         */
        public Double getPrecipProbability() {
            return precipProbability;
        }

        /**
         *
         * @param precipProbability
         * The precipProbability
         */
        public void setPrecipProbability(Double precipProbability) {
            this.precipProbability = precipProbability;
        }

        /**
         *
         * @return
         * The precipType
         */
        public String getPrecipType() {
            return precipType;
        }

        /**
         *
         * @param precipType
         * The precipType
         */
        public void setPrecipType(String precipType) {
            this.precipType = precipType;
        }

        /**
         *
         * @return
         * The temperature
         */
        public Double getTemperature() {
            return temperature;
        }

        /**
         *
         * @param temperature
         * The temperature
         */
        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }

        /**
         *
         * @return
         * The apparentTemperature
         */
        public Double getApparentTemperature() {
            return apparentTemperature;
        }

        /**
         *
         * @param apparentTemperature
         * The apparentTemperature
         */
        public void setApparentTemperature(Double apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }

        /**
         *
         * @return
         * The dewPoint
         */
        public Double getDewPoint() {
            return dewPoint;
        }

        /**
         *
         * @param dewPoint
         * The dewPoint
         */
        public void setDewPoint(Double dewPoint) {
            this.dewPoint = dewPoint;
        }

        /**
         *
         * @return
         * The humidity
         */
        public Double getHumidity() {
            return humidity;
        }

        /**
         *
         * @param humidity
         * The humidity
         */
        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }

        /**
         *
         * @return
         * The windSpeed
         */
        public Double getWindSpeed() {
            return windSpeed;
        }

        /**
         *
         * @param windSpeed
         * The windSpeed
         */
        public void setWindSpeed(Double windSpeed) {
            this.windSpeed = windSpeed;
        }

        /**
         *
         * @return
         * The windBearing
         */
        public Integer getWindBearing() {
            return windBearing;
        }

        /**
         *
         * @param windBearing
         * The windBearing
         */
        public void setWindBearing(Integer windBearing) {
            this.windBearing = windBearing;
        }

        /**
         *
         * @return
         * The visibility
         */
        public Double getVisibility() {
            return visibility;
        }

        /**
         *
         * @param visibility
         * The visibility
         */
        public void setVisibility(Double visibility) {
            this.visibility = visibility;
        }

        /**
         *
         * @return
         * The cloudCover
         */
        public Double getCloudCover() {
            return cloudCover;
        }

        /**
         *
         * @param cloudCover
         * The cloudCover
         */
        public void setCloudCover(Double cloudCover) {
            this.cloudCover = cloudCover;
        }

        /**
         *
         * @return
         * The pressure
         */
        public Double getPressure() {
            return pressure;
        }

        /**
         *
         * @param pressure
         * The pressure
         */
        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }

        /**
         *
         * @return
         * The ozone
         */
        public Double getOzone() {
            return ozone;
        }

        /**
         *
         * @param ozone
         * The ozone
         */
        public void setOzone(Double ozone) {
            this.ozone = ozone;
        }

    }
   public static class Daily {
   public static class Datum_ {

            @SerializedName("time")
            @Expose
            private Integer time;
            @SerializedName("summary")
            @Expose
            private String summary;
            @SerializedName("icon")
            @Expose
            private String icon;
            @SerializedName("sunriseTime")
            @Expose
            private Integer sunriseTime;
            @SerializedName("sunsetTime")
            @Expose
            private Integer sunsetTime;
            @SerializedName("moonPhase")
            @Expose
            private Double moonPhase;
            @SerializedName("precipIntensity")
            @Expose
            private Double precipIntensity;
            @SerializedName("precipIntensityMax")
            @Expose
            private Double precipIntensityMax;
            @SerializedName("precipIntensityMaxTime")
            @Expose
            private Integer precipIntensityMaxTime;
            @SerializedName("precipProbability")
            @Expose
            private Double precipProbability;
            @SerializedName("precipType")
            @Expose
            private String precipType;
            @SerializedName("temperatureMin")
            @Expose
            private Double temperatureMin;
            @SerializedName("temperatureMinTime")
            @Expose
            private Integer temperatureMinTime;
            @SerializedName("temperatureMax")
            @Expose
            private Double temperatureMax;
            @SerializedName("temperatureMaxTime")
            @Expose
            private Integer temperatureMaxTime;
            @SerializedName("apparentTemperatureMin")
            @Expose
            private Double apparentTemperatureMin;
            @SerializedName("apparentTemperatureMinTime")
            @Expose
            private Integer apparentTemperatureMinTime;
            @SerializedName("apparentTemperatureMax")
            @Expose
            private Double apparentTemperatureMax;
            @SerializedName("apparentTemperatureMaxTime")
            @Expose
            private Integer apparentTemperatureMaxTime;
            @SerializedName("dewPoint")
            @Expose
            private Double dewPoint;
            @SerializedName("humidity")
            @Expose
            private Double humidity;
            @SerializedName("windSpeed")
            @Expose
            private Double windSpeed;
            @SerializedName("windBearing")
            @Expose
            private Integer windBearing;
            @SerializedName("visibility")
            @Expose
            private Double visibility;
            @SerializedName("cloudCover")
            @Expose
            private Double cloudCover;
            @SerializedName("pressure")
            @Expose
            private Double pressure;
            @SerializedName("ozone")
            @Expose
            private Double ozone;

            /**
             *
             * @return
             * The time
             */
            public Integer getTime() {
                return time;
            }

            /**
             *
             * @param time
             * The time
             */
            public void setTime(Integer time) {
                this.time = time;
            }

            /**
             *
             * @return
             * The summary
             */
            public String getSummary() {
                return summary;
            }

            /**
             *
             * @param summary
             * The summary
             */
            public void setSummary(String summary) {
                this.summary = summary;
            }

            /**
             *
             * @return
             * The icon
             */
            public String getIcon() {
                return icon;
            }

            /**
             *
             * @param icon
             * The icon
             */
            public void setIcon(String icon) {
                this.icon = icon;
            }

            /**
             *
             * @return
             * The sunriseTime
             */
            public Integer getSunriseTime() {
                return sunriseTime;
            }

            /**
             *
             * @param sunriseTime
             * The sunriseTime
             */
            public void setSunriseTime(Integer sunriseTime) {
                this.sunriseTime = sunriseTime;
            }

            /**
             *
             * @return
             * The sunsetTime
             */
            public Integer getSunsetTime() {
                return sunsetTime;
            }

            /**
             *
             * @param sunsetTime
             * The sunsetTime
             */
            public void setSunsetTime(Integer sunsetTime) {
                this.sunsetTime = sunsetTime;
            }

            /**
             *
             * @return
             * The moonPhase
             */
            public Double getMoonPhase() {
                return moonPhase;
            }

            /**
             *
             * @param moonPhase
             * The moonPhase
             */
            public void setMoonPhase(Double moonPhase) {
                this.moonPhase = moonPhase;
            }

            /**
             *
             * @return
             * The precipIntensity
             */
            public Double getPrecipIntensity() {
                return precipIntensity;
            }

            /**
             *
             * @param precipIntensity
             * The precipIntensity
             */
            public void setPrecipIntensity(Double precipIntensity) {
                this.precipIntensity = precipIntensity;
            }

            /**
             *
             * @return
             * The precipIntensityMax
             */
            public Double getPrecipIntensityMax() {
                return precipIntensityMax;
            }

            /**
             *
             * @param precipIntensityMax
             * The precipIntensityMax
             */
            public void setPrecipIntensityMax(Double precipIntensityMax) {
                this.precipIntensityMax = precipIntensityMax;
            }

            /**
             *
             * @return
             * The precipIntensityMaxTime
             */
            public Integer getPrecipIntensityMaxTime() {
                return precipIntensityMaxTime;
            }

            /**
             *
             * @param precipIntensityMaxTime
             * The precipIntensityMaxTime
             */
            public void setPrecipIntensityMaxTime(Integer precipIntensityMaxTime) {
                this.precipIntensityMaxTime = precipIntensityMaxTime;
            }

            /**
             *
             * @return
             * The precipProbability
             */
            public Double getPrecipProbability() {
                return precipProbability;
            }

            /**
             *
             * @param precipProbability
             * The precipProbability
             */
            public void setPrecipProbability(Double precipProbability) {
                this.precipProbability = precipProbability;
            }

            /**
             *
             * @return
             * The precipType
             */
            public String getPrecipType() {
                return precipType;
            }

            /**
             *
             * @param precipType
             * The precipType
             */
            public void setPrecipType(String precipType) {
                this.precipType = precipType;
            }

            /**
             *
             * @return
             * The temperatureMin
             */
            public Double getTemperatureMin() {
                return temperatureMin;
            }

            /**
             *
             * @param temperatureMin
             * The temperatureMin
             */
            public void setTemperatureMin(Double temperatureMin) {
                this.temperatureMin = temperatureMin;
            }

            /**
             *
             * @return
             * The temperatureMinTime
             */
            public Integer getTemperatureMinTime() {
                return temperatureMinTime;
            }

            /**
             *
             * @param temperatureMinTime
             * The temperatureMinTime
             */
            public void setTemperatureMinTime(Integer temperatureMinTime) {
                this.temperatureMinTime = temperatureMinTime;
            }

            /**
             *
             * @return
             * The temperatureMax
             */
            public Double getTemperatureMax() {
                return temperatureMax;
            }

            /**
             *
             * @param temperatureMax
             * The temperatureMax
             */
            public void setTemperatureMax(Double temperatureMax) {
                this.temperatureMax = temperatureMax;
            }

            /**
             *
             * @return
             * The temperatureMaxTime
             */
            public Integer getTemperatureMaxTime() {
                return temperatureMaxTime;
            }

            /**
             *
             * @param temperatureMaxTime
             * The temperatureMaxTime
             */
            public void setTemperatureMaxTime(Integer temperatureMaxTime) {
                this.temperatureMaxTime = temperatureMaxTime;
            }

            /**
             *
             * @return
             * The apparentTemperatureMin
             */
            public Double getApparentTemperatureMin() {
                return apparentTemperatureMin;
            }

            /**
             *
             * @param apparentTemperatureMin
             * The apparentTemperatureMin
             */
            public void setApparentTemperatureMin(Double apparentTemperatureMin) {
                this.apparentTemperatureMin = apparentTemperatureMin;
            }

            /**
             *
             * @return
             * The apparentTemperatureMinTime
             */
            public Integer getApparentTemperatureMinTime() {
                return apparentTemperatureMinTime;
            }

            /**
             *
             * @param apparentTemperatureMinTime
             * The apparentTemperatureMinTime
             */
            public void setApparentTemperatureMinTime(Integer apparentTemperatureMinTime) {
                this.apparentTemperatureMinTime = apparentTemperatureMinTime;
            }

            /**
             *
             * @return
             * The apparentTemperatureMax
             */
            public Double getApparentTemperatureMax() {
                return apparentTemperatureMax;
            }

            /**
             *
             * @param apparentTemperatureMax
             * The apparentTemperatureMax
             */
            public void setApparentTemperatureMax(Double apparentTemperatureMax) {
                this.apparentTemperatureMax = apparentTemperatureMax;
            }

            /**
             *
             * @return
             * The apparentTemperatureMaxTime
             */
            public Integer getApparentTemperatureMaxTime() {
                return apparentTemperatureMaxTime;
            }

            /**
             *
             * @param apparentTemperatureMaxTime
             * The apparentTemperatureMaxTime
             */
            public void setApparentTemperatureMaxTime(Integer apparentTemperatureMaxTime) {
                this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
            }

            /**
             *
             * @return
             * The dewPoint
             */
            public Double getDewPoint() {
                return dewPoint;
            }

            /**
             *
             * @param dewPoint
             * The dewPoint
             */
            public void setDewPoint(Double dewPoint) {
                this.dewPoint = dewPoint;
            }

            /**
             *
             * @return
             * The humidity
             */
            public Double getHumidity() {
                return humidity;
            }

            /**
             *
             * @param humidity
             * The humidity
             */
            public void setHumidity(Double humidity) {
                this.humidity = humidity;
            }

            /**
             *
             * @return
             * The windSpeed
             */
            public Double getWindSpeed() {
                return windSpeed;
            }

            /**
             *
             * @param windSpeed
             * The windSpeed
             */
            public void setWindSpeed(Double windSpeed) {
                this.windSpeed = windSpeed;
            }

            /**
             *
             * @return
             * The windBearing
             */
            public Integer getWindBearing() {
                return windBearing;
            }

            /**
             *
             * @param windBearing
             * The windBearing
             */
            public void setWindBearing(Integer windBearing) {
                this.windBearing = windBearing;
            }

            /**
             *
             * @return
             * The visibility
             */
            public Double getVisibility() {
                return visibility;
            }

            /**
             *
             * @param visibility
             * The visibility
             */
            public void setVisibility(Double visibility) {
                this.visibility = visibility;
            }

            /**
             *
             * @return
             * The cloudCover
             */
            public Double getCloudCover() {
                return cloudCover;
            }

            /**
             *
             * @param cloudCover
             * The cloudCover
             */
            public void setCloudCover(Double cloudCover) {
                this.cloudCover = cloudCover;
            }

            /**
             *
             * @return
             * The pressure
             */
            public Double getPressure() {
                return pressure;
            }

            /**
             *
             * @param pressure
             * The pressure
             */
            public void setPressure(Double pressure) {
                this.pressure = pressure;
            }

            /**
             *
             * @return
             * The ozone
             */
            public Double getOzone() {
                return ozone;
            }

            /**
             *
             * @param ozone
             * The ozone
             */
            public void setOzone(Double ozone) {
                this.ozone = ozone;
            }

        }

        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("data")
        @Expose
        private List<Datum_> data = new ArrayList<Datum_>();

        /**
         *
         * @return
         * The summary
         */
        public String getSummary() {
            return summary;
        }

        /**
         *
         * @param summary
         * The summary
         */
        public void setSummary(String summary) {
            this.summary = summary;
        }

        /**
         *
         * @return
         * The icon
         */
        public String getIcon() {
            return icon;
        }

        /**
         *
         * @param icon
         * The icon
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }

        /**
         *
         * @return
         * The data
         */
        public List<Datum_> getData() {
            return data;
        }

        /**
         *
         * @param data
         * The data
         */
        public void setData(List<Datum_> data) {
            this.data = data;
        }

    }

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("currently")
    @Expose
    private Currently currently;
    @SerializedName("hourly")
    @Expose
    private Hourly hourly;
    @SerializedName("daily")
    @Expose
    private Daily daily;
    @SerializedName("flags")
    @Expose
    private Flags flags;

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     *
     * @return
     * The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return
     * The currently
     */
    public Currently getCurrently() {
        return currently;
    }

    /**
     *
     * @param currently
     * The currently
     */
    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    /**
     *
     * @return
     * The hourly
     */
    public Hourly getHourly() {
        return hourly;
    }

    /**
     *
     * @param hourly
     * The hourly
     */
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    /**
     *
     * @return
     * The daily
     */
    public Daily getDaily() {
        return daily;
    }

    /**
     *
     * @param daily
     * The daily
     */
    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    /**
     *
     * @return
     * The flags
     */
    public Flags getFlags() {
        return flags;
    }

    /**
     *
     * @param flags
     * The flags
     */
    public void setFlags(Flags flags) {
        this.flags = flags;
    }

}

 class Flags {

    @SerializedName("sources")
    @Expose
    private List<String> sources = new ArrayList<String>();
    @SerializedName("isd-stations")
    @Expose
    private List<String> isdStations = new ArrayList<String>();
    @SerializedName("madis-stations")
    @Expose
    private List<String> madisStations = new ArrayList<String>();
    @SerializedName("units")
    @Expose
    private String units;

    /**
     *
     * @return
     * The sources
     */
    public List<String> getSources() {
        return sources;
    }

    /**
     *
     * @param sources
     * The sources
     */
    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    /**
     *
     * @return
     * The isdStations
     */
    public List<String> getIsdStations() {
        return isdStations;
    }

    /**
     *
     * @param isdStations
     * The isd-stations
     */
    public void setIsdStations(List<String> isdStations) {
        this.isdStations = isdStations;
    }

    /**
     *
     * @return
     * The madisStations
     */
    public List<String> getMadisStations() {
        return madisStations;
    }

    /**
     *
     * @param madisStations
     * The madis-stations
     */
    public void setMadisStations(List<String> madisStations) {
        this.madisStations = madisStations;
    }

    /**
     *
     * @return
     * The units
     */
    public String getUnits() {
        return units;
    }

    /**
     *
     * @param units
     * The units
     */
    public void setUnits(String units) {
        this.units = units;
    }

}

 class Hourly {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    /**
     *
     * @return
     * The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     * The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

}