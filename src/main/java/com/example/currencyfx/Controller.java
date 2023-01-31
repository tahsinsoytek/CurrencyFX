package com.example.currencyfx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.SelectionMode;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    XYChart.Series<String,Float> series=new XYChart.Series<>();
    @FXML
    private BarChart<String,Float> barChart;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXListView<CurrencyUnit> jfxlistView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        DataReader.getInstance().dataPulling();
        jfxlistView.getItems().setAll(DataReader.getList());
        System.out.println(DataReader.getList());
        jfxlistView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        jfxlistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CurrencyUnit>()
        {
            @Override
            public void changed(ObservableValue<? extends CurrencyUnit> observableValue, CurrencyUnit currencyUnit, CurrencyUnit t1) {
                if(jfxlistView.getSelectionModel().getSelectedItem()!=null)
                {
                    addButton.setDisable(false);
                }
                else
                {
                    addButton.setDisable(true);
                }
            }
        });

    }
    @FXML
    void addClicked()
    {
        ObservableList<CurrencyUnit> list;
        list=jfxlistView.getSelectionModel().getSelectedItems();
        if(!barChart.getData().isEmpty())
        removeDuplicate(list);
        try
        {
            for(int i=0;i<list.size();i++)
            {
                String name=list.get(i).getName();
                float value=list.get(i).getValue();
                series=new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(name,value));
                series.setName(list.get(i).getName());
                barChart.getData().add(series);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void clearClicked()
    {
        barChart.getData().clear();
    }
    private void removeDuplicate(ObservableList<CurrencyUnit> list)
    {
        boolean b;
        for(int i=0;i<barChart.getData().size();i++)
        {
            b=true;
            for(int j=0;j<list.size()&&b;j++)
            {
                if(barChart.getData().get(i).getData().get(0).getXValue().equals(list.get(j).getName()))
                {
                    barChart.getData().remove(i);
                    b=false;
                }
            }

        }
    }


}