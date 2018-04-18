#define IRRX A0

int val = 0;
void setup() {
  Serial.begin(9600);
}

void loop() {
  int r = analogRead(IRRX);
  if( abs(r - val) > 0){
    Serial.println(r);
    val = r;
    delay(100);
  }
}
