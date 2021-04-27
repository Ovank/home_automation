#include <ESP8266WebServer.h>
#include <ESP8266WiFi.h>
#include <ESP8266mDNS.h>
#include <WiFiClient.h>


ESP8266WebServer servver(80);
/*IPAddress ip(192, 168, 0, 3); //set static ip
IPAddress gateway(192, 168, 0, 1); //set getteway
IPAddress subnet(255, 255, 255, 0);//set subnet*/
String  i;
int p=0;
WiFiServer server(80);
void setup()
{
  i = "";

Serial.begin(9600);
pinMode(5, OUTPUT);
pinMode(4, OUTPUT);
pinMode(0, OUTPUT);
pinMode(2, OUTPUT);
pinMode(14, OUTPUT);
pinMode(12, OUTPUT);
pinMode(13, OUTPUT);
pinMode(15, OUTPUT);
WiFi.disconnect();
  delay(3000);
   WiFi.begin("The MainFrame","manassir");
  Serial.println("Connecting");
  while ((!(WiFi.status() == WL_CONNECTED))){
    delay(300);
    Serial.print(".");
 }
// WiFi.config(ip, gateway, subnet);
  Serial.println("Connected");
  Serial.println("Your IP is");
  Serial.println((WiFi.localIP().toString()));
  if (MDNS.begin("esp8266")) {              // Start the mDNS responder for esp8266.local
    Serial.println("mDNS responder started");
  } else {
    Serial.println("Error setting up MDNS responder!");
  }
  server.begin(); 
  servver.begin();
  //Serial.println("HTTP server started");
}


void loop()
{
    //servver.handleClient();
    WiFiClient client = server.available();
    if (!client) { 
    return; }
    while(!client.available()){
      //Serial.println("Mohit");
      delay(1); 
      }
    i = (client.readStringUntil('\r'));
    //Serial.println(i[2],i);
    i.remove(0, 5);
    i.remove(i.length()-9,9);
    Serial.println(i);
    Serial.println(int(i[2])-48);
    Serial.println(i[2]);
    if(i[1]=='0')
    {
      //Serial.println("Status worked");
      if(WiFi.status() == WL_CONNECTED)
      {
        //Serial.print("the value of p ");
        Serial.print(p);
        if(p==0)
        {
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("");
          client.println("OFF");
          client.stop();
        }
        else
        {
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("");
          client.println("ON");
          client.stop();
        }
        
      }
      else
      {
        Serial.println("Error in Wifi");
      }
    }
    if(i[1]=='1')
    {
        //Serial.println(p);
        Serial.println("Mohit"+i[2]);
        if(p==0)
        {
          p=1;
          Serial.println("Hardware teaam rock");
          digitalWrite(int(i[2])-48,HIGH);
          digitalWrite(i[2],HIGH);
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("");
          client.println("OFF");
          client.stop();
        }
        else
        {
          Serial.println("om mistake");
          p=0;
          digitalWrite(i[2],LOW);
          digitalWrite(int(i[2])-48,LOW);
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("");
          client.println("ON");
          client.stop();    
        }
        
    }
}
