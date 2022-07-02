package com.crio.shorturl;

import java.util.*;

public class XUrlImpl implements XUrl {

    private HashMap<String, String> longToShortMap = new HashMap<>();

    private HashMap<String, String> shortToLongMap = new HashMap<>();

    private HashMap<String, Integer> countUrl = new HashMap<>();


    public String registerNewUrl(String longUrl) {

    
        if (this.longToShortMap.containsKey(longUrl)) {
            return this.longToShortMap.get(longUrl);
        }

        Random r = new Random();
        String shortUrl = "http://short.url/"
                + Long.toString(r.nextLong() & Long.MAX_VALUE, 30).toUpperCase().substring(0, 9);

        this.longToShortMap.put(longUrl, shortUrl);
        this.shortToLongMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String registerNewUrl(String longUrl, String shortUrl) {

        if (this.shortToLongMap.containsKey(shortUrl)) {
            return null;
        }
        this.shortToLongMap.put(shortUrl, longUrl);
        this.longToShortMap.put(longUrl, shortUrl);
        return shortUrl;
    }

    public String getUrl(String shortUrl) {
        
        if (this.shortToLongMap.containsKey(shortUrl)) {
            String longUrl = this.shortToLongMap.get(shortUrl);

            if (this.countUrl.containsKey(longUrl)) {
                this.countUrl.put(longUrl, this.countUrl.get(longUrl) + 1);
            }
            else {
                this.countUrl.put(longUrl, 1);
            }
            return this.shortToLongMap.get(shortUrl);
        
        }
        return null;
    }
    
    public Integer getHitCount(String longUrl) {

        if (this.countUrl.containsKey(longUrl)) {
            
            return this.countUrl.get(longUrl);
        }
        return 0;
    }

    public String delete(String longUrl) {
        this.shortToLongMap.remove(this.longToShortMap.get(longUrl));
        this.longToShortMap.remove(longUrl);
        return null;
    }

}

