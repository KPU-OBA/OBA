
#include <Wire.h>
#include <Adafruit_SSD1306.h>
#include <Adafruit_GFX.h>

// OLED display TWI address
#define OLED_ADDR   0x3C

Adafruit_SSD1306 display(-1);

#if (SSD1306_LCDHEIGHT != 64)
#error("Height incorrect, please fix Adafruit_SSD1306.h!");
#endif

#define IRRX A0 //적외선 센서 수광부

//LED
const int blueLed = 11;
const int greenLed = 12;
const int redLed = 13;

//Button
const int button_01 = 1;
const int button_02 = 2;

//State
boolean lastButton_01=LOW; //이전 버튼의 눌림 상태를 Boolean형 변수로 선언
boolean currentButton_01=LOW; //현재 버튼의 눌림 상태를 Boolean형 변수로 선언

boolean lastButton_02=LOW; //이전 버튼의 눌림 상태를 Boolean형 변수로 선언
boolean currentButton_02=LOW; //현재 버튼의 눌림 상태를 Boolean형 변수로 선언

int sensorButtonCount = 0;
int displayButtonCount = 0;
int val = 0;


void setup(){
  Serial.begin(9600); //Serial Monitor

  //적외선 발광부
  pinMode(greenLed, OUTPUT);
  pinMode(redLed, OUTPUT);
  pinMode(blueLed, OUTPUT);
  
  digitalWrite(greenLed, LOW);
  digitalWrite(redLed, LOW);
  digitalWrite(blueLed, LOW);

  //버튼
  pinMode(button_01, INPUT);
  pinMode(button_02, INPUT);

  //OLED LCD
  display.begin(SSD1306_SWITCHCAPVCC, OLED_ADDR);
  display.clearDisplay();
  display.display();

  //oledDisplay(displayButtonCount);
  // display a pixel in each corner of the screen
  display.drawPixel(0, 0, WHITE);
  display.drawPixel(127, 0, WHITE);
  display.drawPixel(0, 63, WHITE);
  display.drawPixel(127, 63, WHITE);

  // display a line of text
  //display.setTextSize(3);
  //display.setTextColor(WHITE);
  //display.setCursor(0,20);
  //display.print("Forward");
  // update display with all of the above graphics
  //display.display();
}


void loop(){
  int r = analogRead(IRRX);
  //int buttonCount;
  if( abs(r - val) > 0){
    //Serial.println(r);
    val = r;
    delay(100);
  }
  
  currentButton_01 = button_01_Debounce(lastButton_01); //디바운싱 처리된 버튼값을 읽음
  if(lastButton_01 == LOW && currentButton_01 == HIGH) //버튼을 누름
  {
    sensorButtonCount++;
    displayButtonCount++;
    Serial.println("=====================");
    Serial.println("Button_01");
    Serial.print("buttonCount: ");
    Serial.println(displayButtonCount);
    Serial.println("=====================");
    oledDisplay(displayButtonCount);
  }
  lastButton_01=currentButton_01; //이전 버튼값을 현재 버튼값으로 변경

  currentButton_02 = button_02_Debounce(lastButton_02); //디바운싱 처리된 버튼값을 읽음
  if(lastButton_02 == LOW && currentButton_02 == HIGH) //버튼을 누름
  {
    sensorButtonCount--;
    displayButtonCount--;
    Serial.println("=====================");
    Serial.println("Button_02");
    Serial.print("buttonCount: ");
    Serial.println(displayButtonCount);
    Serial.println("=====================");
    oledDisplay(displayButtonCount);
  }
  lastButton_02=currentButton_02; //이전 버튼값을 현재 버튼값으로 변경

  ledSwitching(sensorButtonCount);
}


boolean button_01_Debounce(boolean last){
  boolean current=digitalRead(button_01); //현재 버튼 상태를 확인
  if(last!=current) //이전 버튼 상태와 현재 버튼 상태가 다름
  {
    delay(20); //20ms 동안 기다림
    current=digitalRead(button_01);//current에 현재 버튼 상태를 저장
  }
  return current; //current 변수값을 반환
}


boolean button_02_Debounce(boolean last){
  boolean current=digitalRead(button_02); //현재 버튼 상태를 확인
  if(last!=current) //이전 버튼 상태와 현재 버튼 상태가 다름
  {
    delay(20); //20ms 동안 기다림
    current=digitalRead(button_02);//current에 현재 버튼 상태를 저장
  }
  return current; //current 변수값을 반환
}


//LED스위칭
void ledSwitching(int btnCount){
  switch (btnCount) {
    case 0:
      sensorButtonCount = 7;
      break;
    case 1:
      digitalWrite(greenLed, HIGH);
      digitalWrite(redLed, LOW);
      digitalWrite(blueLed, LOW);
      break;
    case 2:
      digitalWrite(greenLed, LOW);
      digitalWrite(redLed, HIGH);
      digitalWrite(blueLed, LOW);
      break;
    case 3:
      digitalWrite(greenLed, HIGH);
      digitalWrite(redLed, HIGH);
      digitalWrite(blueLed, LOW);
      break;
    case 4:
      digitalWrite(greenLed, LOW);
      digitalWrite(redLed, LOW);
      digitalWrite(blueLed, HIGH);
      break;
    case 5:
      digitalWrite(greenLed, HIGH);
      digitalWrite(redLed, LOW);
      digitalWrite(blueLed, HIGH);
      break;
    case 6:
      digitalWrite(greenLed, LOW);
      digitalWrite(redLed, HIGH);
      digitalWrite(blueLed, HIGH);
      break;
    case 7:
      digitalWrite(greenLed, HIGH);
      digitalWrite(redLed, HIGH);
      digitalWrite(blueLed, HIGH);
      break;
    case 8:
      sensorButtonCount = 1;
      break;
    default:
      digitalWrite(greenLed, LOW);
      digitalWrite(redLed, LOW);
      digitalWrite(blueLed, LOW);
  }
}

//OLED_LCD
void oledDisplay(int btnCount){
  //display.begin(SSD1306_SWITCHCAPVCC, OLED_ADDR);
  display.clearDisplay();
  //display.display();
  
  display.setTextSize(2);
  display.setTextColor(WHITE);
  display.setCursor(0,20);
  switch (btnCount) {
    case 0:
      display.print("7 is Empty");
      display.display();
      displayButtonCount = 7;
      break;
    case 1:
      display.print("Forward");
      display.display();
      break;
    case 2:
      display.print("Turn Left");
      display.display();
      break;
    case 3:
      display.print("Turn Right");
      display.display();
      break;
    case 4:
      display.print("Function");
      display.display();
      break;
    case 5:
      display.print("Loop");
      display.display();
      break;
    case 6:
      display.print("6 is Empty");
      display.display();
      break;
    case 7:
      display.print("7 is Empty");
      display.display();
      break;
    case 8:
      display.print("Forward");
      display.display();
      displayButtonCount = 1;
      break;
    default:
      display.clearDisplay();
      display.display();
  }
}
