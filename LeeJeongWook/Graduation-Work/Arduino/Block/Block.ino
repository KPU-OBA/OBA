#define IRRX A0

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

int buttonCount = 0;
int val = 0;

void setup(){
  Serial.begin(9600);
  pinMode(greenLed, OUTPUT);
  pinMode(redLed, OUTPUT);
  pinMode(blueLed, OUTPUT);

  pinMode(button_01, INPUT);
  pinMode(button_02, INPUT);
  
  digitalWrite(greenLed, LOW);
  digitalWrite(redLed, LOW);
  digitalWrite(blueLed, LOW);
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
    buttonCount++;
    Serial.println("=====================");
    Serial.println("Button_01");
    Serial.print("buttonCount: ");
    Serial.println(buttonCount);
    Serial.println("=====================");
  }
  lastButton_01=currentButton_01; //이전 버튼값을 현재 버튼값으로 변경

  currentButton_02 = button_02_Debounce(lastButton_02); //디바운싱 처리된 버튼값을 읽음
  if(lastButton_02 == LOW && currentButton_02 == HIGH) //버튼을 누름
  {
    buttonCount--;
    Serial.println("=====================");
    Serial.println("Button_02");
    Serial.print("buttonCount: ");
    Serial.println(buttonCount);
    Serial.println("=====================");
  }
  lastButton_02=currentButton_02; //이전 버튼값을 현재 버튼값으로 변경

  ledSwitching(buttonCount);
}


boolean button_01_Debounce(boolean last){
  boolean current=digitalRead(button_01); //현재 버튼 상태를 확인
  if(last!=current) //이전 버튼 상태와 현재 버튼 상태가 다름
  {
    delay(20); //5ms 동안 기다림
    current=digitalRead(button_01);//current에 현재 버튼 상태를 저장
  }
  return current; //current 변수값을 반환
}

boolean button_02_Debounce(boolean last){
  boolean current=digitalRead(button_02); //현재 버튼 상태를 확인
  if(last!=current) //이전 버튼 상태와 현재 버튼 상태가 다름
  {
    delay(20); //5ms 동안 기다림
    current=digitalRead(button_02);//current에 현재 버튼 상태를 저장
  }
  return current; //current 변수값을 반환
}

void ledSwitching(int btnCount){
  //LED스위칭

  switch (btnCount) {
    case 0:
      buttonCount += 7;
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
      buttonCount -= 7;
      break;
    default:
      digitalWrite(greenLed, LOW);
      digitalWrite(redLed, LOW);
      digitalWrite(blueLed, LOW);
  }
  //return buttonCount;
}
