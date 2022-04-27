package me.tungexplorer.study.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

public interface Subject {

    void register(Observer o);

    void remove(Observer o);

    void notifyObservers();
}

interface Observer {
    void update(int temp, int humidity);
}

class WeatherStation implements Subject {
    private final List<Observer> observers;
    private int temp;
    private int humidity;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void remove(Observer o) {
        int observerIndex = observers.indexOf(o);
        if (observerIndex >= 0) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update(temp, humidity));
    }

    public void measurementsChanged(int temp, int humidity) {
        this.temp = temp;
        this.humidity = humidity;
        notifyObservers();
    }
}

class CurrentConditionsDisplay implements Observer {

    private int temp;
    private int humidity;

    public CurrentConditionsDisplay(Subject weatherStation) {
        weatherStation.register(this);
    }

    @Override
    public void update(int temp, int humidity) {
        this.temp = temp;
        this.humidity = humidity;
        displayCurrent();
    }

    private void displayCurrent() {
        System.out.println("Current temperature: " + temp);
        System.out.println("Current humidity: " + humidity);
    }
}

class ForecastDisplay implements Observer {
    private final List<Integer> tempHistory;
    private final List<Integer> humidityHistory;

    public ForecastDisplay(Subject weatherStation) {
        tempHistory = new ArrayList<>();
        humidityHistory = new ArrayList<>();
        weatherStation.register(this);
    }

    @Override
    public void update(int temp, int humidity) {
        this.tempHistory.add(temp);
        this.humidityHistory.add(humidity);
        display7DayHistory();
    }

    private void display7DayHistory() {
        System.out.println("Temperature History: " + tempHistory.subList(Math.max(tempHistory.size() - 7, 0), tempHistory.size()));
        System.out.println("Humidity History: " + humidityHistory.subList(Math.max(humidityHistory.size() - 7, 0), humidityHistory.size()));
    }
}

class ObserverDemoMain {
    @SneakyThrows
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherStation);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherStation);

        // simulate update
        for (int i = 0; i < 5; i++) {
            System.out.println("\n --- Update " + i + " ---");
            int randomTemp = getRandomInt(-50, 40);
            int randomHumidity = getRandomInt(0, 100);
            weatherStation.measurementsChanged(randomTemp, randomHumidity);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static int getRandomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max + 1 - min) + min;
    }
}