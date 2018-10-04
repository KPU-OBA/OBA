#include <stdio.h>
#include <wiringPi.h>
#include <mcp3004.h>


#define Up 0
#define Down 1
#define Left 2
#define Right 3
#define Function 4

int val1_1,val1_2,val1_3 = 0;
int val2_1,val2_2,val2_3 = 0;
int val3_1,val3_2,val3_3 = 0;
int val4_1,val4_2,val4_3 = 0;
int val5_1,val5_2,val5_3 = 0;


int route[5];


int main(){

    wiringPiSetup();
    mcp3004Setup(300,0);
    mcp3004Setup(308,1);

    int i = 0;
    
    while(1)
	{
		val1_1 = analogRead(300);
		val1_2 = analogRead(301);
		val1_3 = analogRead(302);

		val2_1 = analogRead(303);
		val2_2 = analogRead(304);
		val2_3 = analogRead(305);

		val3_1 = analogRead(306);
		val3_2 = analogRead(307);
		val3_3 = analogRead(308);


		val4_1 = analogRead(309);
		val4_2 = analogRead(310);
		val4_3 = analogRead(311);

		val5_1 = analogRead(312);
		val5_2 = analogRead(313);
		val5_3 = analogRead(314);

		printf("==================\n");
		printf("ch1_1:%d\n",val1_1);
		printf("ch1_2:%d\n",val1_2);
		printf("ch1_3:%d\n",val1_3);
		printf("------------------\n");
		printf("ch2_1:%d\n",val2_1);
		printf("ch2_2:%d\n",val2_2);
		printf("ch2_3:%d\n",val2_3);
		printf("------------------\n");
		printf("ch3_1:%d\n",val3_1);
		printf("ch3_2:%d\n",val3_2);
		printf("ch3_3:%d\n",val3_3);
		printf("------------------\n");
		printf("ch4_1:%d\n",val4_1);
		printf("ch4_2:%d\n",val4_2);
		printf("ch4_3:%d\n",val4_3);
		printf("------------------\n");
		printf("ch5_1:%d\n",val5_1);
		printf("ch5_2:%d\n",val5_2);
		printf("ch5_3:%d\n",val5_3);
		printf("==================\n");
		delay(1000);

        //Section_01
        if(val1_1 < 200)
        {
            if(val1_2 < 200)
            {
                route[0] = Left;
                printf("Section_01: Left\n");
            }
            else if(val1_3 <200)
            {
                route[0] = Function;
                printf("Section_01: Function\n");
            }
            else
            {
                route[0] = Up;
                printf("Section_01: Up\n");
            }
        }
        else if(val1_2 < 200)
        {
            route[0] = Down;
            printf("Section_01: Down\n");
        }
        else if(val1_3 < 200)
        {
            route[0] = Right;
            printf("Section_01: Right\n");
        }
        else
        {
            printf("Put Block in Section_01\n");
        }
        
        //Section_02
        if(val2_1 < 200)
        {
            if(val2_2 < 200)
            {
                route[1] = Left;
                printf("Section_02: Left\n");
            }
            else if(val2_3 <200)
            {
                route[1] = Function;
                printf("Section_02: Function\n");
            }
            else
            {
                route[1] = Up;
                printf("Section_02: Up\n");
            }
        }
        else if(val2_2 < 200)
        {
            route[1] = Down;
            printf("Section_02: Down\n");
        }
        else if(val2_3 < 200)
        {
            route[1] = Right;
            printf("Section_02: Right\n");
        }
        else
        {
            printf("Put Block in Section_02\n");
        }
        
        //Section_03
        if(val3_1 < 200)
        {
            if(val3_2 < 200)
            {
                route[2] = Left;
                printf("Section_02: Left\n");
            }
            else if(val3_3 <200)
            {
                route[2] = Function;
                printf("Section_02: Function\n");
            }
            else
            {
                route[2] = Up;
                printf("Section_02: Up\n");
            }
        }
        else if(val3_2 < 200)
        {
            route[2] = Down;
            printf("Section_02: Down\n");
        }
        else if(val3_3 < 200)
        {
            route[2] = Right;
            printf("Section_02: Right\n");
        }
        else
        {
            printf("Put Block in Section_02\n");
        }
        
        //Section_04
        if(val4_1 < 200)
        {
            if(val4_2 < 200)
            {
                route[3] = Left;
                printf("Section_02: Left\n");
            }
            else if(val4_3 <200)
            {
                route[3] = Function;
                printf("Section_02: Function\n");
            }
            else
            {
                route[3] = Up;
                printf("Section_02: Up\n");
            }
        }
        else if(val4_2 < 200)
        {
            route[3] = Down;
            printf("Section_02: Down\n");
        }
        else if(val4_3 < 200)
        {
            route[3] = Right;
            printf("Section_02: Right\n");
        }
        else
        {
            printf("Put Block in Section_02\n");
        }
        
        //Section_05
        if(val5_1 < 200)
        {
            if(val5_2 < 200)
            {
                route[4] = Left;
                printf("Section_02: Left\n");
            }
            else if(val5_3 <200)
            {
                route[4] = Function;
                printf("Section_02: Function\n");
            }
            else
            {
                route[4] = Up;
                printf("Section_02: Up\n");
            }
        }
        else if(val5_2 < 200)
        {
            route[4] = Down;
            printf("Section_02: Down\n");
        }
        else if(val5_3 < 200)
        {
            route[4] = Right;
            printf("Section_02: Right\n");
        }
        else
        {
            printf("Put Block in Section_02\n");
        }
        
        for(i=0; i<5; i++)
        {
            printf("%d, ", route[i]);
        }
        printf("\n");

	}

	return 0;
}
