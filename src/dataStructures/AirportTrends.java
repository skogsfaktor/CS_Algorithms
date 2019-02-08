/*
package org.apache.flink.quickstart;

import com.dataartisans.flinktraining.exercises.datastream_java.basics.RideCleansing;
import com.dataartisans.flinktraining.exercises.datastream_java.datatypes.TaxiRide;
import com.dataartisans.flinktraining.exercises.datastream_java.sources.TaxiRideSource;
import com.dataartisans.flinktraining.exercises.datastream_java.utils.GeoUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.*;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.scala.KeyedStream;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.Calendar;
import java.util.TimeZone;


public class AirportTrends {

    public enum JFKTerminal {
        //All of these are specific cell ID's denoting terminals.
        TERMINAL_1(71436),
        TERMINAL_2(71688),
        TERMINAL_3(71191),
        TERMINAL_4(70945),
        TERMINAL_5(70190),
        TERMINAL_6(70686),
        NOT_A_TERMINAL(-1);

        //Received cell id
        int mapGrid;

        private JFKTerminal(int grid){
            this.mapGrid = grid;
        }

        //just a static function
        public static JFKTerminal gridToTerminal(int grid){
            //Iterate through the terminals, checking which one the cell id corresponds to, if any.
            for(JFKTerminal terminal : values()){
                if(terminal.mapGrid == grid) return terminal;
            }
            //Need to watch out for this, and filter these out, probably in a seperate function.
            return NOT_A_TERMINAL;
        }
    }

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // get the taxi ride data stream - Note: you got to change the path to your local data file
        DataStream<TaxiRide> rides = env.addSource(new TaxiRideSource("nycTaxiRides.gz", 60, 2000));

        */
/**
         * Write your application here
         *//*

        //Convert timestamp to hour of day.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        //calendar.setTimeInMillis(longTimestamp);
        calendar.get(Calendar.HOUR_OF_DAY);

        DataStream<Tuple3<JFKTerminal, Integer, Integer>> trends = rides
                //Filter NYC, returns (something, arrival/departure, time, location(long, lat)
                .filter(new RideCleansing.NYCFilter())
                //Convert to grid, now we have (Integer, Boolean)
                .map(new AirportTrends.GridCellMatcher())
                //Convert grid to terminals, now we have (JFKTerminal, Boolean)
                .map(new AirportTrends.GridToTerminal())
                //Remove shitty terminals
                .filter(new FilterFunction<Tuple2<JFKTerminal, Boolean>>() {
                    @Override
                    public boolean filter(Tuple2<JFKTerminal, Boolean> count) throws Exception {
                        return count.f0 != JFKTerminal.NOT_A_TERMINAL;
                    }
                })
                .keyBy(0, 1)
                .timeWindow(Time.minutes(60))
                .apply(new AirportTrends.RideCounter());

        DataStream<Tuple3<JFKTerminal, Integer, Integer>> mostPopular = trends
                .timeWindowAll(Time.minutes(60))
                .maxBy(1);

        //trends.print();
        mostPopular.print();

        //(TERMINAL_6,9,4), output
        env.execute();

    }

    public static class GridToTerminal implements MapFunction<Tuple2<Integer, Boolean>, Tuple2<JFKTerminal, Boolean>> {

        //Takes a taxiRide event, returns a tuple with (cellID, arrival/departure)
        @Override
        public Tuple2<JFKTerminal, Boolean> map(Tuple2<Integer, Boolean> taxiRide) throws Exception {
            JFKTerminal terminal = JFKTerminal.gridToTerminal(taxiRide.f0);
            return new Tuple2<>(terminal, taxiRide.f1);
        }
    }

    */
/**
     * Map taxi ride to grid cell and event type.
     * Start records use departure location, end record use arrival location.
     *//*

    public static class GridCellMatcher implements MapFunction<TaxiRide, Tuple2<Integer, Boolean>> {

        //Takes a taxiRide event, returns a tuple with (cellID, arrival/departure)
        @Override
        public Tuple2<Integer, Boolean> map(TaxiRide taxiRide) throws Exception {
            if(taxiRide.isStart) {
                // get grid cell id for start location/departure
                int gridId = GeoUtils.mapToGridCell(taxiRide.startLon, taxiRide.startLat);
                return new Tuple2<>(gridId, true);
            } else {
                // get grid cell id for end location/arrival
                int gridId = GeoUtils.mapToGridCell(taxiRide.endLon, taxiRide.endLat);
                return new Tuple2<>(gridId, false);
            }
        }
    }

    */
/**
     * Counts the number of rides arriving or departing.
     *//*

    public static class RideCounter implements WindowFunction<
            Tuple2<JFKTerminal, Boolean>, // input type
            Tuple3<JFKTerminal, Integer, Integer>, // output type
            Tuple, // key type
            TimeWindow> // window type
    {

        @SuppressWarnings("unchecked")
        @Override
        public void apply(
                Tuple key,
                TimeWindow window,
                Iterable<Tuple2<JFKTerminal, Boolean>> values,
                Collector<Tuple3<JFKTerminal, Integer, Integer>> out) throws Exception {

            JFKTerminal cellId = ((Tuple2<JFKTerminal, Boolean>)key).f0;
            boolean isStart = ((Tuple2<JFKTerminal, Boolean>)key).f1;
            long windowTime = window.getEnd();

            int cnt = 0;
            for(Tuple2<JFKTerminal, Boolean> v : values) {
                cnt += 1;
            }

            //Convert timestamp to hour
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));
            calendar.setTimeInMillis(windowTime);

            out.collect(new Tuple3<>(cellId, cnt, calendar.get(Calendar.HOUR_OF_DAY)));
        }
    }

    */
/**
     * Maps the grid cell id back to longitude and latitude coordinates.
     *//*

    public static class GridToCoordinates implements
            MapFunction<Tuple4<Integer, Long, Boolean, Integer>, Tuple5<Float, Float, Long, Boolean, Integer>> {

        //Takes a tuple with (cellid, timestamp, travel type, count)
        //Returns
        @Override
        public Tuple5<Float, Float, Long, Boolean, Integer> map(Tuple4<Integer, Long, Boolean, Integer> cellCount) throws Exception {

            return new Tuple5<>(
                    //Convert back
                    GeoUtils.getGridCellCenterLon(cellCount.f0),
                    GeoUtils.getGridCellCenterLat(cellCount.f0),
                    //Get the rest of them(remember that is's 0 index)
                    cellCount.f1,
                    cellCount.f2,
                    cellCount.f3);
        }
    }
}*/
