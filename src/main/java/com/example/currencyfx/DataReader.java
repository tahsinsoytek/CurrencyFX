package com.example.currencyfx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class DataReader
{
    private static ArrayList<CurrencyUnit> list=new ArrayList<>();
    static private URL url;
    String line;
    static DataReader instance=new DataReader(null, new ResourceBundle()
    {
        @Override
        protected Object handleGetObject(String key) {
            return null;
        }

        @Override
        public Enumeration<String> getKeys() {
            return null;
        }
    });
    private DataReader(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            this.url=new URL("https://www.tcmb.gov.tr/kurlar/today.xml");
        }
        catch(Exception e)
        {

        }
    }
    public static DataReader getInstance()
    {
        return instance;
    }
    public static ArrayList<CurrencyUnit> getList()
    {
        return new ArrayList<CurrencyUnit>(list);
    }

    public void dataPulling()
    {
        try
        {
            HttpURLConnection hr=(HttpURLConnection) url.openConnection();
            try (InputStream inputStream = hr.getInputStream())
            {
                int unit;
                String name;
                float value;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while((line = bufferedReader.readLine()) != null)
                {
                    if(line.contains("CrossOrder")&&!(line.contains("XDR")))
                    {
                        line=bufferedReader.readLine();
                        line=line.replaceAll("<Unit>","");
                        line=line.replaceAll("</Unit>","");
                        line=line.trim();
                        unit=Integer.parseInt(line);
                        line=bufferedReader.readLine();
                        line=line.replaceAll("<Isim>","");
                        line=line.replaceAll("</Isim>","");
                        line=line.trim();
                        name=line;
                        line=bufferedReader.readLine();
                        line=bufferedReader.readLine();
                        line=bufferedReader.readLine();
                        line=line.replaceAll("<ForexSelling>","");
                        line=line.replaceAll("</ForexSelling>","");
                        line=line.trim();
                        value=Float.parseFloat(line);
                        list.add(new CurrencyUnit(name,value,unit));
                    }
                }


            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}

