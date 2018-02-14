#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <Adafruit_Sensor.h>
#include "DHT.h"

#define DHTTYPE DHT11   // DHT 11

// Your network data
const char* ssid = "SSID";
const char* password = "PASSWORD";

const int DHTPin = 2;

DHT dht(DHTPin, DHTTYPE);

static char celsiusTemp[7];
static char humidityTemp[7];

void setup() {
  Serial.begin(9600);
  delay(10);

  dht.begin();
  
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  delay(3000);
  
  Serial.println(WiFi.localIP());
}

// runs over and over again
void loop() {
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();

  float hic = dht.computeHeatIndex(t, h, false);       
  dtostrf(hic, 6, 2, celsiusTemp); 
  dtostrf(h, 6, 2, humidityTemp);

  //for check/debug purpose
  /*Serial.print(t);
  Serial.print(", ");
  Serial.println(h);*/

  if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status
 
    HTTPClient http;  //Declare an object of class HTTPClient

    String tempStr = String(t);
    String humStr = String(h);

    String addRoomWeatherApi = String("http://192.168.1.107:8080/api/add/room-weather?temp=" + tempStr + "&humidity=" + humStr);
 
    http.begin(addRoomWeatherApi);
    int httpCode = http.GET();
 
    if (httpCode > 0) {
      Serial.println("ok");
    }
 
    http.end();   //Close connection
 
  }

  delay(3600000); //every 1 hour
}