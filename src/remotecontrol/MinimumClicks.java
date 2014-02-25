package remotecontrol;


/**
 Date:24 February, 2014.
 Created by: Prashanthi Errabelly.
 File:MinimumClicks.java
 */

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
public class MinimumClicks {
	static int savedVariableLength=0;
	static int savedNumbers[]=new int[1000];
	static int currentChannel=0;
	public static void main(String args[]) throws IOException{
		int lowChannelNumber;
		int highChannelNumber; 
		int blockedChannels[] = new int[40];
		int viewChannels[] = new int[50];
		int startChannelNumber=0;
		
		int clicks=0;
		
		int minimumClicks=0;
		



		System.out.println("Enter low channel number!");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String value1=br.readLine();
		lowChannelNumber=Integer.parseInt(value1);

		System.out.println("Enter high channel number!");
		String value2=br.readLine();
		highChannelNumber=Integer.parseInt(value2);


		System.out.println("Enter Number of blocked channels!");
		String blockedOnes=br.readLine();
		int numberForBlockedOnes=Integer.parseInt(blockedOnes);
		System.out.println("Enter blocked channel numbers!");
		for (int i=0;i<numberForBlockedOnes;i++){
			String s=br.readLine();
			blockedChannels[i]=Integer.parseInt(s);
		}

		System.out.println("Enter Number of view channels!");
		String viewOnes=br.readLine();
		int numberForViewedOnes=Integer.parseInt(viewOnes);
		System.out.println("Enter viewed channel numbers!");
		for (int i=0;i<numberForViewedOnes;i++){
			String s=br.readLine();
			viewChannels[i]=Integer.parseInt(s);
		}






		System.out.println("low Channel Number"+lowChannelNumber);
		System.out.println("high channel number"+highChannelNumber);
		System.out.println("Blocked channel Numbers are:");
		for(int i=0;i<numberForBlockedOnes;i++){
			System.out.print(" "+blockedChannels[i]);
		}
		System.out.println();
		System.out.println("Viewed channel Numbers are:");
		for(int i=0;i<numberForViewedOnes;i++){
			System.out.print(" "+viewChannels[i]);
		}
		System.out.println();
		
		


		if(doValidation(lowChannelNumber,highChannelNumber,numberForBlockedOnes,blockedChannels,numberForViewedOnes,viewChannels)){
			//System.out.println("Validation success for your input!!");
			//finding Starting channel number.
			startChannelNumber=getStartChannelNumber(numberForBlockedOnes,lowChannelNumber,blockedChannels,highChannelNumber);
			currentChannel=startChannelNumber;
			System.out.println("Hey!! u got start channel num as "+startChannelNumber);

			
			currentChannel=up(currentChannel,numberForBlockedOnes,blockedChannels,lowChannelNumber,highChannelNumber);
			clicks++;
			currentChannel=down(currentChannel,numberForBlockedOnes,blockedChannels,lowChannelNumber,highChannelNumber);
			clicks++;
			currentChannel=back();
			clicks++;
			
			
			System.out.println("You have clicked up and down button and your current channel number is "+currentChannel+" with clicks "+clicks);
			

		}


	}

	private static int getStartChannelNumber(int numberForBlockedOnes, int lowChannelNumber, int[] blockedChannels, int highChannelNumbe) {
		int startChannelNumber=0;
		//boolean satisfy=false;
		while(lowChannelNumber<=highChannelNumbe){
			boolean valid=true;
			for(int i=0;i<numberForBlockedOnes && valid;i++){
				if(lowChannelNumber==blockedChannels[i]){
					valid=false;
					break;	
				}

			}if(valid){
				startChannelNumber=lowChannelNumber;
				//satisfy=true;
				break;
			}
			lowChannelNumber++;
		}
		//	if(!satisfy){
		//		System.out.println("Sorry!! I could not find the start channel :-( .......");
		//	}
		return startChannelNumber;
	}

	private static int down(int currentChannel, int numberForBlockedOnes, int[] blockedChannels, int lowChannelNumber, int highChannelNumber) {
		int channelNumber=currentChannel;
		
        boolean isOver=true;
		for(int j=0;j<(highChannelNumber-lowChannelNumber);j++){
			if(channelNumber==lowChannelNumber){

				channelNumber=highChannelNumber;

			}else{
				channelNumber=channelNumber-1;
			}
			for(int i=0;i<numberForBlockedOnes;i++){
				if(channelNumber==blockedChannels[i]){
					isOver=false;
					break;
				}
			isOver=true;	
			}
			if(isOver){
				break;
			}
		}
	
		if(savedVariableLength==0){
			
		savedNumbers[savedVariableLength]=channelNumber;
		savedVariableLength++;
		}
		else{
			
			
		savedNumbers[savedVariableLength]=channelNumber;
		savedVariableLength++;
		}
		
		return channelNumber;
	}


	private static int up(int currentChannel, int numberForBlockedOnes, int[] blockedChannels, int lowChannelNumber, int highChannelNumber) {
		int channelNumber=currentChannel;
        boolean isOver=true;
		for(int j=0;j<(highChannelNumber-lowChannelNumber);j++){
			if(channelNumber==highChannelNumber){

				channelNumber=lowChannelNumber;

			}else{
				channelNumber=channelNumber+1;
			}
			for(int i=0;i<numberForBlockedOnes;i++){
				if(channelNumber==blockedChannels[i]){
					isOver=false;
					break;
				}
			isOver=true;	
			}
			if(isOver){
				break;
			}
		}
		if(savedVariableLength==0){
			savedNumbers[savedVariableLength]=channelNumber;
			
			savedVariableLength++;
			}else{
				
			savedNumbers[savedVariableLength]=channelNumber;
			
			savedVariableLength++;
			}
	
		return channelNumber;
	}

		private static int back() {
currentChannel=savedNumbers[--savedVariableLength];

return currentChannel;
		}

	private static boolean doValidation(int lowChannelNumber,
			int highChannelNumber, int numberForBlockedOnes, int[] blockedChannels, int numberForViewedOnes, int[] viewChannels) {
		boolean isValid=true;
		StringBuffer messages=new StringBuffer();


		if(!(lowChannelNumber<=1000 && lowChannelNumber>=1)){
			messages.append("Please enter lowest channel number between 1-1000.");
			messages.append("\n");

		}
		if(!(highChannelNumber<=1000 && highChannelNumber>=1 && highChannelNumber>=lowChannelNumber)){
			messages.append("Please enter highest channel number from 1-1000 and it must be same or greater than lowest channel number");
			messages.append("\n");

		}
		if((blockedChannels.length<=40)){

			int min=blockedChannels[0];
			int max=blockedChannels[numberForBlockedOnes-1];

			for (int i = 0; i < numberForBlockedOnes; i++) {

				if (min >= blockedChannels[i]) {
					min = blockedChannels[i];
				}

				if (max <= blockedChannels[i]) {
					max = blockedChannels[i];
				}
			}

			if(!(min>=lowChannelNumber && max<=highChannelNumber)){

				messages.append("Please enter blocked channel numbers properly!!");
				messages.append("\n");

			}
		}

		if(!(blockedChannels.length<=40)){
			messages.append("Number of blocked channel numbers must not be greater than 40.");
			messages.append("\n");
		}

		if((viewChannels.length<=50)){

			int min=viewChannels[0];
			int max=viewChannels[numberForViewedOnes-1];
			boolean valid=true;
			for (int i = 0; i < numberForViewedOnes; i++) {

				if (min >= viewChannels[i]) {
					min = viewChannels[i];
				}
				if (max <= viewChannels[i]) {
					max = viewChannels[i];
				}
				for(int j=0; j<numberForBlockedOnes ; j++){
					if(viewChannels[i]==blockedChannels[j]){
						valid=false;
					}
				}

			}
			if(!(min>=lowChannelNumber && max<=highChannelNumber && valid)){
				messages.append("Please enter viewed channel numbers properly!!");
				messages.append("\n");

			}
		}
		if(!(viewChannels.length<=50)){
			messages.append("Number of viewed channel numbers must not be greater than 50.");
			messages.append("\n");

		}


		if(!(messages.toString()==null || messages.toString().equals(""))){
			System.out.println(messages.toString());
			isValid=false;
		}

		return isValid;



	}
}
