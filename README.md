# SmartHouse
Weather status for house and city I am living in. Later will add other features

## How to start ?
To use this project you need to change at least 2 things:
- file 'SecurityConfig' - you need to configure your 'AuthenticationManagerBuilder' (please read comments in that file)
- you need to add application.properties - I left lets say pattern file called 'config.properties' you can change it and rename

## Connection diagram
I use in this project 'Orange PI Zero' as a server (I installed Debian, update Java to '8', and configure Wi-Fi so it's start working itself after rebooting thats mostly all I changed)

I also use ESP8266 and DHT11 sensor (temperature & humidty) the connection looks like this:
![Connection diagram](https://raw.githubusercontent.com/heya10/SmartHouse/master/connection_diagram.png)
The dashed line means that I use there 'USB - micro USB' cable to provide power for ESP8266 (in my case ESP8266 module and Orange PI Zero are close to each other so its easier to use USB port as a 'power provider')

## Comments
If you wonder why in the ESP8266 project the value for 'DHTPin is 2 and on the diagram the connection pin is D4 (digital 4) I want to inform you - it is NOT mistake. The mapping of Arduino & ESP8266 boards looks like that (Arduino D4 = DHTPin 2)