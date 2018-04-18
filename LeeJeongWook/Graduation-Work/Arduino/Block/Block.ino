const int blueLed = 11;
const int greenLed = 12;
const int redLed = 13;

const int button01 = 1;
const int button02 = 2;

int button01_State = 0;
int button02_State = 0;
int buttonCount = 0;

void setup(){
  Serial.begin(9600);
  pinMode(greenLed, OUTPUT);
  pinMode(redLed, OUTPUT);
  
  pinMode(greenLed, OUTPUT);
  pinMode(redLed, OUTPUT);

  digitalWrite(greenLed, LOW);
  digitalWrite(redLed, LOW);
}

void loop(){
  button01_State = digitalRead(button01);
  button02_State = digitalRead(button02);

  //button01_State = HIGH;
  //button02_State = HIGH;
  //Serial.println("helo");

  
  if(button01_State == LOW){
    //buttonCount++;
    //return buttonCount;
    Serial.println(button01_State);
    Serial.println("Button_01");
    return 0;
  }
  else{
    Serial.println("Delay...");
    delay(1000);
  }
  /*
  else if(button02_State == LOW){
    buttonCount--;
    //return buttonCount;
    Serial.println(buttonCount);
    Serial.println("Button_02");
  }
  */
/*
  if(button01_State == LOW){
    digitalWrite(greenLed, HIGH);
  }
  else{
    digitalWrite(greenLed,LOW);
  }

  if(button02_State == LOW){
    digitalWrite(redLed, HIGH);
  }
  else{
    digitalWrite(redLed,LOW);
  }
  */
}

