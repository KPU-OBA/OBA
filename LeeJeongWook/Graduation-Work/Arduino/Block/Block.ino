const int greenLed = 12;
const int redLed = 13;

const int button01 = 1;
const int button02 = 2;

int button01_State = 0;
int button02_State = 0;
int buttonCount = 0;

void setup(){
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
}

