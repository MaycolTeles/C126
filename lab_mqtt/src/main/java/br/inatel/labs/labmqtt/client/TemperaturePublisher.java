package br.inatel.labs.labmqtt.client;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;
import java.util.UUID;

public class TemperaturePublisher
{
    public static void main(String[] args) throws MqttException
    {
        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = new MqttClient(MyConstants.URI_BROKER, publisherId);

        MqttMessage msg = getTemperatureSolo();
        msg.setQos(0);
        msg.setRetained(true);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);

        publisher.publish(MyConstants.TOPIC_1, msg);

        publisher.disconnect();
    }

    private static MqttMessage getTemperatureSolo()
    {
        Random r = new Random();
        double temperature = 80 + r.nextDouble() * 20.0;
        byte[] payload = String.format("T:%04.2f", temperature).getBytes();
        return new MqttMessage(payload);
    }
}