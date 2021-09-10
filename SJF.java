import java.util.*; // for input
 
public class SJF {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of process: ");
		int n = sc.nextInt();
		int type;  // for the type of algorithm either preemptive or non preemptive
		int pid[] = new int[n]; 	// process ids
		int at[] = new int[n]; 		//  arrival time
		int bt[] = new int[n]; 		// burst time
		int ct[] = new int[n]; 		// complete time
		int ta[] = new int[n]; 		// turn around time
		int wt[] = new int[n]; 	    //waiting time
		
		String[] status = new String[50];
		int statusCount = 0;
		int flag2 = 0;

		int f[] = new int[n];       // flag to check whether process is completed or not
		int k[]= new int[n];  		 // it is also stores brust time
		int i, st=0, tot=0;            // tot will hold if sall the process were entertained or not
		//int[] btt = new int[n]; // copy of BurstTime array. . . 
			// tot = number or process that are completed
		float avgwt=0, avgta=0;
 		
 		System.out.print("Enter 1 for Non-Preemptive Algorithm\nEnter 2 for Peemptive Algorithm(ShortestRemainingTimeFirst)\nChoice: ");
		type = sc.nextInt();
		

		for(i=0;i<n;i++){
			System.out.print("P" + (i+1) + " Arrival Time:");
			at[i] = sc.nextInt();
			System.out.print("P" + (i+1) + " Burst Time:");
			bt[i] = sc.nextInt();
			k[i] = bt[i];
			pid[i] = i+1; // ids of process are added
			f[i] = 0;   // process is not completed yet
		}
		if(type==1){	
			boolean a = true; // a is boolean it will check whether all the process were entertained or not
			while(true){
				int c=n, min=999;
				if (tot == n) // total no of process = completed process loop will be terminated
					break;
				
				for (i=0; i<n; i++){
					/*
					 * If i'th process arrival time <= system time and its flag=0 and burst<min 
					 * That process will be executed first 
					 */ 
					if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min)){
						min=bt[i];   // shortest Burst Time
						c=i;
						////////////////////////////Holded the Status of arrived process////////////////////////////////////
						status[statusCount] = at[i]+" P"+pid[i]+" Arrived";
						statusCount++;
					}
				}
				
				/* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
				if (c==n){ 
					st++;
				}

				else{
					if(tot==0){
						flag2 = (at[tot]+1);
						status[statusCount] = (at[tot]+1)+" P"+pid[tot]+" Running";
						statusCount++;
					}
					else{
						status[statusCount] = flag2+" P"+pid[tot]+" Waiting";
						statusCount++;	
					}
					ct[c]=st+bt[c];  //complete time  = current time + burstTime of current process
					st+=bt[c];       // now changing the current time to current time + burstTime
					ta[c]=ct[c]-at[c];  // calculating the turn around time
					wt[c]=ta[c]-bt[c];   // waiting time calculated and stored
					f[c]=1;          // since the process is complete therefore making flag = 1  for that index
					tot++;           // total number of completed process is incremented
				}
			}
			status[statusCount] = (ct[0]+1)+" P"+pid[0]+" Exit";
			statusCount++;
			System.out.println("\nPId  Arrival Burst Complete TurnAround Waiting");
			for(i=0;i<n;i++){
				avgwt+= wt[i];
				avgta+= ta[i];
				System.out.println("P"+pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+wt[i]);
			}
			// bubble sort for status message alignment
				String tempor;
				int tempInt =Integer.parseInt(status[0].charAt(0)+"");
				for(int b=0; b<statusCount; b++){
					tempInt =Integer.parseInt(status[b].charAt(0)+"");
					for(int j=0; j<statusCount; j++){
						if(tempInt<(Integer.parseInt((status[j].charAt(0)+""+status[j].charAt(1)).trim()))){  // bubble sort algorithm
				
						tempor = status[b];
						status[b] = status[j];
						status[j] = tempor;
						}
					}	
				}
				// removing duplications
				for(int g=0; g<statusCount; g++){
					for(int h=g+1; h<statusCount; h++){
						if(status[g].equals(status[h])){
							status[g] = " ";
						}
					}
				}
			System.out.println("ST P Status");  // System Time ProcessId Status
			for(int x=0; x<statusCount; x++){

				if(status[x].equals(" ")){

					}
					else
						System.out.println(status[x]);
				
			}

		}
		else if(type==2){




			while(true){
			    int min=99,c=n;
			    if(tot==n)
			    	break;
			    	for(i=0;i<n;i++){
			    		if((at[i]<=st) && (f[i]==0) && (bt[i]<min)){	
			    			min=bt[i];
			    			c=i;
			    			status[statusCount] = at[i]+" P"+pid[i]+" Arrived";
							statusCount++;
			    		}
			    	}
			    	if (c==n)
			    		st++;
			    	else{
			    		if(tot==0){
							flag2 = (at[tot]+1);
							status[statusCount] = (at[tot]+1)+" P"+pid[tot]+" Running";
							statusCount++;
						}
						else{
							status[statusCount] = flag2+" P"+pid[tot]+" Waiting";
							statusCount++;	
						}
			    		bt[c]--;
			    		st++;
			    		if (bt[c]==0){
			    			ct[c]= st;
			    			f[c]=1;
			    			tot++;
			    		}
			    	}
			    }
			   	status[statusCount] = (ct[0]+1)+" P"+pid[0]+" Exit";
				statusCount++;
			 
			    for(i=0;i<n;i++){
			    	ta[i] = ct[i] - at[i];
			    	wt[i] = ta[i] - k[i];
			    	avgwt+= wt[i];
			    	avgta+= ta[i];
			    }

			    System.out.println("\nPId  Arrival Burst Complete TurnAround Waiting");
				for(i=0;i<n;i++){
	    			System.out.println(pid[i] +"\t"+ at[i]+"\t"+ k[i] +"\t"+ ct[i] +"\t"+ ta[i] +"\t"+ wt[i]);
	    		}
	    		// not aligned
				
				// bubble sort for status message alignment
				String tempor;
				int tempInt =Integer.parseInt(status[0].charAt(0)+"");
				for(int b=0; b<statusCount; b++){
					tempInt =Integer.parseInt(status[b].charAt(0)+"");
					for(int j=0; j<statusCount; j++){
						if(tempInt<(Integer.parseInt((status[j].charAt(0)+""+status[j].charAt(1)).trim()))){  // bubble sort algorithm
				
						tempor = status[b];
						status[b] = status[j];
						status[j] = tempor;
						}
					}	
				}
				
				// removing duplications
				for(int g=0; g<statusCount; g++){
					for(int h=g+1; h<statusCount; h++){
						if(status[g].equals(status[h])){
							status[g] = " ";
						}
					}
				}
				System.out.println("ST P Status");  // System Time ProcessId Status	
				for(int x=0; x<statusCount; x++){
					if(status[x].equals(" ")){

					}
					else
						System.out.println(status[x]);
				}
			}
	}
}