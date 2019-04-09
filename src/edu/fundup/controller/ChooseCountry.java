/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.VBox;

/**
 *
 * @author hhamzaoui
 */
public class ChooseCountry extends VBox{
      private static final Random RND = new Random();
    private static final double MIN_VALUE = 100;
    private static final double MAX_VALUE = 500;
    private static int noOfNodes = 0;
    private Tile worldTile;
    private Tile countryTile;

    private List<ChartData> data;
    private List<BarChartItem> worldDataOfSelectedYear;

}
