package AndroidVersion;

	//2017.04.09 2017.04.11 2017.04.12 2017.04.13  2017.04.24 2017.04.25 
    //2017.05.10 begin the final version, gather them and make it more clear
	//BeibeiZhang
	//version1.KetyCalculation:demo of 4*4 and delete three labels
	//version2. 8*8 formal version
	//to prove:1 delete all the connected labels (at most 5)
	//2 limit the exchange:only in the case that two continuous clicked buttons are neighbors that can do the exchange
	//3 remove the part that if when there are 3 or more than 3 labels are the same, users can delete them by one click
	//4 add txt window that record the foots and the target scores 
	//6 change the layout by box 
	//7 set the size of the txt and labels bt setfont()
	//8 when we get the goal, then exit the procedure

	import java.awt.Button;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
	import java.awt.event.ActionEvent;
			import java.awt.event.ActionListener;

		import java.util.ArrayList;

			import javax.swing.*;
			public class GAme1 {
				
				 static int steps;
				 static int scores=0;
				 static int targets;
				 static ImageIcon[] icons=new ImageIcon[5];
				 static ImageIcon[] rollover=new ImageIcon[5];
				 static int inDex;
				 static String returnmessage; 
			    public GAme1(int mainsteps,int maintargets,String begin){
			    	steps=mainsteps;
			    	targets=maintargets;
			    	returnmessage=begin;
			  
			    }
				//private variables can be used in the whole class 
				//so we can use them in listener
				//but local variable can only be used in the function
				//and if we want to use them in main 
				//they need to be static
				public static Boolean isRightBound(int place){
					//use this func to judge the button is not at the four edges of the panel
					//or there will be no buttons[place+1] and the system will show the mistake 
					Boolean noright=false;
					int[] right={7,15,23,31,39,47,55,63};
					for(int Right:right){
						if(place==Right){
							noright=true;
						}
					}
					return noright;
				}
				
				
				public static Boolean isLeftBound(int place){
					Boolean noleft=false;
					int[] left={0,8,16,24,32,40,48,56};
					for(int Left:left){
						if(place==Left){
							noleft=true;
						}
					}
					return noleft;
				}
				
				
				public static Boolean isUpBound(int place){
					Boolean noup=false;
					int[] up={0,1,2,3,4,5,6,7};
					for(int Up:up){
						if(place==Up){
							noup=true;
						}
					}
					return noup;
				}
				
				
				public static Boolean isDownBound(int place){
					Boolean nodown=false;
					int[] down={56,57,58,59,60,61,62,63};
					for(int Down:down){
						if(place==Down){
							nodown=true;
						}
					}
					return nodown;
				}
				
				
				public static Boolean ifneighbor(int place1,int place2){
					Boolean neighbor=false;
					if((place1==place2-1)||(place1==place2+1)||(place1==place2-8)||(place1==place2+8)){
						neighbor=true;
					}
					return neighbor;
				}
			
				
				 static void Findsamearound(int place,JButton[] buttons,ArrayList<Integer> places){
					 if(places.isEmpty()){
						 places.add(place);
						
					 }
					 else{
						 Boolean existed0=false;
			             for(int i=0;i<=(places.size()-1);i++){
			           
			              if(places.get(i)==(place)){
			            	  existed0=true;
			            	  
			            	  break;
			              }
				  }
			             if(existed0==false){
			            	 places.add(place);  
			            
			             }
			             else{
			            	 
			             } 
					 }
			         if(isRightBound(place)==false){
					                        if(buttons[place].getIcon().equals(buttons[place+1].getIcon())){
					                         Boolean existed=false;
					                         for(int i=0;i<=(places.size()-1);i++){
					        	              if(places.get(i).equals(place+1)){
					        	            	  existed=true;
					        	            	  break;
					        	              }
					           	  }
					                         if(existed==false){
					                        	 places.add(place+1);
						        	             Findsamearound(place+1,buttons,places);
					                         }
					                         else{
					                        	 
					                         }
					                         }
					              }
					       	   if(isLeftBound(place)==false){
							                if(buttons[place].getIcon().equals(buttons[place-1].getIcon())){
							                	   Boolean existed=false;
							                         for(int i=0;i<=(places.size()-1);i++){
							        	              if(places.get(i).equals(place-1)){
							        	            	  existed=true;
							        	            	  break;
							        	              }
							           	  }
							                         if(existed==false){
							                        	 places.add(place-1);
								        	             Findsamearound(place-1,buttons,places);
							                         }
							                         else{
							                        	 
							                         }
							                         }
							              }
					           if(isUpBound(place)==false){
							                 if(buttons[place].getIcon().equals(buttons[place-8].getIcon())){
							                	   Boolean existed=false;
							                         for(int i=0;i<=(places.size()-1);i++){
							        	              if(places.get(i).equals(place-8)){
							        	            	  existed=true;
							        	            	  break;
							        	              }
							           	  }
							                         if(existed==false){
							                        	 places.add(place-8);
								        	             Findsamearound(place-8,buttons,places);
							                         }
							                         else{
							                        	 
							                         }
							                         }
							             }
						       if(isDownBound(place)==false){
							                if(buttons[place].getIcon().equals(buttons[place+8].getIcon())){
							                	   Boolean existed=false;
							                         for(int i=0;i<=(places.size()-1);i++){
							        	              if(places.get(i).equals(place+8)){
							        	            	  existed=true;
							        	            	  break;
							        	              }
							           	  }
							                         if(existed==false){
							                        	 places.add(place+8);
								        	             Findsamearound(place+8,buttons,places);
							                         }
							                         else{
							                        	 
							                         }
							                         }
							            }
					            	 
						 }
		
			
				public static Boolean twiceclickdelete(JButton[] buttons,JTextField score,int place1,int place2){
					//if there are three or more than three same labels that are connected then delete them by if nesting(Ƕ��)
				    Boolean deleted=false;
					int[] places={place1,place2};
									for(int i:places){
						
		     			 ArrayList<Integer> sameLabelPlace=new ArrayList<Integer>();
						 sameLabelPlace.clear();
	                     Findsamearound(i,buttons,sameLabelPlace);	
	                     int length=sameLabelPlace.size();
	                     if(length>=3){
				      			for(int n=0;n<=length-1;n++){
				      				buttons[sameLabelPlace.get(n)].setIcon(null);
				      				buttons[sameLabelPlace.get(n)].setRolloverIcon(null);
				      			
				      			}
				      			deleted=true;
				      		}
				      		else{
				            		}
				   
				  
					}//for(int i:places){
					try{
						Thread.sleep(1000);
					}catch(InterruptedException ecx){
						ecx.printStackTrace();
					}
		             //in this place, we need to stop for 0.8s, or the code runs too fast, you will not see
					  //the elimination(����) of the labels
					int deletenumber=0;
					for(int index2=0;index2<=63;index2++){
						if(buttons[index2].getIcon()==null){
							 int randomnumber=(int)(Math.random()*5+1);
							 buttons[index2].setIcon(icons[randomnumber-1]);
							 buttons[index2].setRolloverIcon(rollover[randomnumber-1]);	
	         					 deletenumber++;
														}
						//reset the labels which were deleted before
					}

					try{
						Thread.sleep(1000);
					}catch(InterruptedException ecx){
						ecx.printStackTrace();
					}
					scores=scores+deletenumber;	
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run(){
							score.setText("Scores:"+String.valueOf(scores));
						}
					});
				
					return deleted;
					}
					
			
				
				public static void GAME1(JPanel panel2,JTextField step,JTextField score,JButton exit,Object current){
				  icons[0]=new ImageIcon("icon1.jpg");
				  icons[1]=new ImageIcon("icon2.jpg");
				  icons[2]=new ImageIcon("icon3.jpg");
				  icons[3]=new ImageIcon("icon4.jpg");
				  icons[4]=new ImageIcon("icon5.jpg");
				  rollover[0]=new ImageIcon("icon1rollover.jpg");
				  rollover[1]=new ImageIcon("icon2rollover.jpg");
				  rollover[2]=new ImageIcon("icon3rollover.jpg");
				  rollover[3]=new ImageIcon("icon4rollover.jpg");
				  rollover[4]=new ImageIcon("icon5rollover.jpg");
				 
				  panel2.removeAll();
				  
				  scores=0;
				  score.setText("Scores:"+scores);
				  
			      panel2.setLayout(new GridLayout(8,8));
				
				//In JFrame, we can close the window easily by click the "��"
				//But in Frame, we need to use WindowListener and then clicking the "��" can make difference

				
				 
				  JButton[] buttons=new JButton[64];
				  ArrayList<Icon> iconlabel=new ArrayList<Icon>();
				  ArrayList<Icon> rolloverlabel=new ArrayList<Icon>();
				  ArrayList<Integer> place=new ArrayList<Integer>();
				  for(int index=0;index<=63;index++){
					  int randomnumber=(int)(Math.random()*5+1);
					  buttons[index]=new JButton();
					  buttons[index].setActionCommand(String.valueOf(index));
					  buttons[index].setIcon(icons[randomnumber-1]);
					  buttons[index].setRolloverIcon(rollover[randomnumber-1]);
					  buttons[index].setOpaque(false);  
					  buttons[index].setContentAreaFilled(false);  
					  buttons[index].setMargin(new Insets(0, 0, 0, 0));  
					  buttons[index].setFocusPainted(false);  
					  buttons[index].setBorderPainted(false);  
					  buttons[index].setBorder(null);  
					  panel2.add(buttons[index]);
					  buttons[index].addActionListener(new ActionListener(){
						  public void actionPerformed(ActionEvent ex){
							  Runnable runnable = new Runnable() {
								 
		        				  @Override
		        				  public void run() {
		        					  inDex=Integer.parseInt(ex.getActionCommand());
										 place.add(inDex);
			  if(place.size()>1){
						        Boolean neighbor=ifneighbor(place.get(0),place.get(1));
					  if(neighbor==true){
						    	 iconlabel.add(buttons[place.get(1)].getIcon());
						    	 rolloverlabel.add(buttons[place.get(1)].getRolloverIcon());
				        			
		        				  SwingUtilities.invokeLater(new Runnable() {

		        				  @Override
		        				  public void run() {
		        					  buttons[place.get(0)].setIcon(iconlabel.get(1));
		        					  buttons[place.get(0)].setRolloverIcon(rolloverlabel.get(1));
				    				  buttons[place.get(1)].setIcon(iconlabel.get(0));
				    				  buttons[place.get(1)].setRolloverIcon(rolloverlabel.get(0));
		        				  }
		        				  });
		        				  //if the things we do is about GUI, then we should put these things 
		        				  //on the GUI thread
		        				  //and do other things on the other thread
		        				  try {
		              				
		            				  Thread.sleep(800);
		            				  } catch (InterruptedException e1) {
		            				  e1.printStackTrace();
		            				  }
		        				  //in this place, we need to stop for 0.8s, or the code runs too fast, you will not see
			    				  //the exchange of the two buttons
		        				  Boolean deleted=twiceclickdelete(buttons,score,place.get(0),place.get(1));
			    				    if(deleted==false){
			    					
				    				  //do not go through all the labels and delete
		        				  SwingUtilities.invokeLater(new Runnable() {
		        				  @Override
		        				  public void run() {
		        					  
		        					  buttons[place.get(0)].setIcon(iconlabel.get(0));
		        					  buttons[place.get(0)].setRolloverIcon(rolloverlabel.get(0));
				    				  buttons[place.get(1)].setIcon(iconlabel.get(1));
				    				  buttons[place.get(1)].setRolloverIcon(rolloverlabel.get(1));
				    				  iconlabel.clear();
				    				  place.clear();
				    				  rolloverlabel.clear();

		      				  }
		      				  });
			    					  }
			    				    else{
				    					  steps=steps-1;
				    					  SwingUtilities.invokeLater(new Runnable() {

				            				  @Override
				            				  public void run() {
				            					  step.setText("Steps:"+String.valueOf(steps));	
				            				  }
				            				  });
				    					  if(steps!=0){
				    						  if(scores>=targets){
				    							  returnmessage="win";
				    							  synchronized(current) {
				    		   		      			  
				    		 		      				current.notify();
				    		 		      				
				    		 		      				
				    		 		      				}
				    					 }
				    						  //more scores will be recorded in the next round
				    						  else{
				    							  myCheck(buttons,icons,rollover); 
						    					  //go through all the labels and delete	
						    					  if(notDie(buttons)){
						    					 }
						    					  else{
						    						  returnmessage="dead";
						    						  synchronized(current) {
						    	   		      			  
						    	 		      				current.notify();
						    	 		      				
						    	 		      				
						    	 		      				}
						    					  }
						    					  place.clear();
						    					  iconlabel.clear();
							    				 rolloverlabel.clear();

						    					//check if the game has dead and the player can not go on 
				    						  }
				    						 
				    					  }
				    					  else{
				    						  if(scores>=targets){
				    							 returnmessage="win";
				    							 synchronized(current) {
				    		   		      			  
				    		 		      				current.notify();
				    		 		      				
				    		 		      				
				    		 		      				}
				    						  }
				    						  else{
				    							  returnmessage="lose";
				    							  synchronized(current) {
				    		   		      			  
				    		 		      				current.notify();
				    		 		      				
				    		 		      				
				    		 		      				}
				    						  }
				    						
				    						//exit(0) means exit the procedures commonly
				    						//exit(1) means exit the procedures uncommonly
				    					  }//if(steps==0)
				    					 		  
				    				  }//if(deleted==true)
	                            }//if(neighbor==true)
				    				  else{
				    					  int middle=place.get(1);
				    		       		  place.clear();
				    					  place.add(middle);
				    					  Icon middleicon=buttons[place.get(0)].getIcon();
				    					  Icon middlerollover=buttons[place.get(0)].getRolloverIcon();
				    					  iconlabel.clear();
				    					  rolloverlabel.clear();
				    					  iconlabel.add(middleicon);
				    					  rolloverlabel.add(middlerollover);
					    				  //if click two buttons that are not neighbors, it makes no use
					    				  //until the user click two neighbors continuously, it can make the exchange 
				    				  }//if(neighbor==false)
				    				 	    			  }//if(place.size()>1)
				    			  else{ 
				    				  iconlabel.add(buttons[place.get(0)].getIcon());
				    				  rolloverlabel.add(buttons[place.get(0)].getRolloverIcon());
				    						    			  }//if(place.size()<=1)
		      	
		        				  
		        				  }
		        				  
		        				  };
		        				  new Thread(runnable).start();
		  				  }
					  });
				  }
				  panel2.updateUI();
				  if(returnmessage.equals("begin")){
				 myCheck(buttons,icons,rollover);
				 
				  if(notDie(buttons)){
					  JOptionPane.showMessageDialog(panel2,"We can go on~"); 
					
				  }
				  else{
					returnmessage="dead";
					 synchronized(current) {
  		      			  
		      				current.notify();
		      				
		      				
		      				}
				  }
				  }
				//check if the game has dead and the player can not go on 
				 
				   }
/////////////////////////////////////////////////////////////////////////////
//the following is to check deleted and replace them
//////////////////////////////////////////////////////////////////////////////				
				 public static void myCheck(JButton[] buttons,ImageIcon[] icons,ImageIcon[] rollover){
			      	  int index=0;
			      	  ArrayList<Integer> sameLabelPlace=new ArrayList<Integer>();
			      	  sameLabelPlace.clear();
			      	  for(index=0;index<=63;index++){
			      		sameLabelPlace.clear();
			      		if((buttons[index].getIcon()==null)){
			      			//use "==" not ".equals()"
			      		continue;	
			      		}
			      		else{
			      		  Findsamearound(index,buttons,sameLabelPlace);	
			      			}
			      		int length=sameLabelPlace.size();
			      		length=sameLabelPlace.size();
			      		if(length>=3){
			      			for(int n=0;n<=length-1;n++){
			      				buttons[sameLabelPlace.get(n)].setIcon(null);
			      				buttons[sameLabelPlace.get(n)].setRolloverIcon(null);
			      			}
			      		}
			      		else{
			            		}
			      	  }
			      	  Boolean end=true;
			      	for(index=0;index<=63;index++){
			      		if(buttons[index].getIcon()==null){
			      			end=false;
			      			int randomnumber=(int)(Math.random()*5+1);
			      			buttons[index].setIcon(icons[randomnumber-1]);
						    buttons[index].setRolloverIcon(rollover[randomnumber-1]);
			         		    
			      		}
			      	}
			      	if(end){
			      		
			      	}
			      	else{
			      	
			             	myCheck(buttons,icons,rollover);	
			      		}
			      	}
	//////////////////////////////////////////////////////////////////////////	  
	//the following is about check death
   //////////////////////////////////////////////////////////////////////////
				 public static Boolean notDie(JButton[] buttons){
						JButton[] newbuttons=new JButton[64];
						//all the operations are done on the new Jbutton[]. 
						//so we can just check the initial Jbutton[] and get the result but not change it
						for(int j=0;j<=63;j++){
							newbuttons[j]=new JButton();
							newbuttons[j].setIcon(buttons[j].getIcon());
						}
						Boolean ifdelete=false;
						for(int k=0;k<=63;k++){
							Icon center=newbuttons[k].getIcon();
							if(isRightBound(k)==false){
								Icon right=newbuttons[k+1].getIcon();
								newbuttons[k].setIcon(right);
								newbuttons[k+1].setIcon(center);
								ifdelete=exchangeDelete(newbuttons,k,k+1);
								if(ifdelete){
									return ifdelete;
								}
								else{
									newbuttons[k].setIcon(center);
									newbuttons[k+1].setIcon(right);
								}
							}
				            if(isLeftBound(k)==false){
				            	Icon left=newbuttons[k-1].getIcon();
								newbuttons[k].setIcon(left);
								newbuttons[k-1].setIcon(center);
								ifdelete=exchangeDelete(newbuttons,k,k-1);
								if(ifdelete){
									return ifdelete;
								}
								else{
									newbuttons[k].setIcon(center);
									newbuttons[k-1].setIcon(left);
								}
								
							}
				            if(isDownBound(k)==false){
				            	Icon down=newbuttons[k+8].getIcon();
								newbuttons[k].setIcon(down);
								newbuttons[k+8].setIcon(center);
								ifdelete=exchangeDelete(newbuttons,k,k+8);
								if(ifdelete){
									return ifdelete;
								}
								else{
									newbuttons[k].setIcon(center);
									newbuttons[k+8].setIcon(down);
								}
					
				            }
				            if(isUpBound(k)==false){
				            	Icon up=newbuttons[k-8].getIcon();
								newbuttons[k].setIcon(up);
								newbuttons[k-8].setIcon(center);
								ifdelete=exchangeDelete(newbuttons,k,k-8);
								if(ifdelete){
									return ifdelete;
								}
								else{
									newbuttons[k].setIcon(center);
									newbuttons[k-8].setIcon(up);
								}
					
				            }
						}
						return ifdelete;
					} 
					public static int DoubleSame(JButton[] buttons,Icon label,int place,int samenumber){
					    int number2=0;
					    //number2 presents how many the same neighbors of buttons[place]
					    Boolean markleft=false;
					      //if the left label is the same with this label then the markleft is true
					      Boolean markright=false;
					      Boolean markup=false;
					      Boolean markdown=false;
					      //save the label of buttons[place1] in case buttons[label].setLabel() make difference to the later operation 
					      if(isRightBound(place)==false){
					      if(label.equals(buttons[place+1].getIcon())){
					    	  samenumber++;
					    	  number2++;
					    	  markright=true;
					      }
					      }
					      if(isLeftBound(place)==false){
					    	  if(label.equals(buttons[place-1].getIcon())){
					    	  samenumber++;
					    	  number2++;
					    	  markleft=true;
					      }
					    	  }
					      if(isDownBound(place)==false){
					      if(label.equals(buttons[place+8].getIcon())){
					    	  samenumber++;
					    	  number2++;
					    	  markdown=true;
					      }
					      }
					      if(isUpBound(place)==false){
					      if(label.equals(buttons[place-8].getIcon())){
					    	  samenumber++;
					    	  number2++;
					    	  markup=true;
					      }
					      }
					      //if number2 is 1, we do nothing, it means none of the three neighbors of buttons[place] (except buttons[i])
					      //are the same with label
					      if(number2>=2){
					    	  if(markright){
						    	  buttons[place].setIcon(null);
						    	  buttons[place].setRolloverIcon(null);
						    	  buttons[place+1].setIcon(null);
						    	  buttons[place+1].setRolloverIcon(null);
						    	  //because we have use String 'label' to save/present the buttons[i].label
						    	  //this setlabel() will make no difference
						   	    	  }
					    	  if(markleft){
					    		  buttons[place].setIcon(null);
					    		  buttons[place].setRolloverIcon(null);
						    	  buttons[place-1].setIcon(null);
						    	  buttons[place-1].setRolloverIcon(null);
					  }
					    	  if(markup){
						    	  buttons[place].setIcon(null);
						    	  buttons[place].setRolloverIcon(null);
						    	  buttons[place-8].setIcon(null);
						    	  buttons[place-8].setRolloverIcon(null);
						   }  
					    	  if(markdown){
				  	    	  buttons[place].setIcon(null);
				  	    	 buttons[place].setRolloverIcon(null);
					    	  buttons[place+8].setIcon(null);
					    	  buttons[place+8].setRolloverIcon(null);
					    		  }
					      }
					      return (samenumber-1);
					      //because in the four neighbors of buttons[place], there must is a same neighbor that is buttons[i]
					      //because of this, we make a repetition(�ظ�)
					}

					
						public static Boolean exchangeDelete(JButton[] buttons,int place1,int place2){
							//if there are three or more than three same labels that are connected then delete them by if nesting(Ƕ��)
						    Boolean deleted=false;
							int[] places={place1,place2};
							for(int i:places){
						      int samenumber=1;
						      //record the number of the same neighbors with label place1 (containing buttons[i])
						      Boolean markleft=false;
						      //if the left label is the same with this label then the markleft is true
						      Boolean markright=false;
						      Boolean markup=false;
						      Boolean markdown=false;
						      Icon iconlabel=buttons[i].getIcon();
						      Icon rolloverlabel=buttons[i].getRolloverIcon();
						      //save the label of buttons[place1] in case buttons[label].setLabel() make difference to the later operation 
						      if(isRightBound(i)==false){
						      if(iconlabel.equals(buttons[i+1].getIcon())){
						    	  samenumber++;
						    	  markright=true;
						    	
						      }
						      }
						      if(isLeftBound(i)==false){
						      if(iconlabel.equals(buttons[i-1].getIcon())){
						    	  samenumber++;
						    	  markleft=true;
						      }
						      }
						      if(isDownBound(i)==false){
						      if(iconlabel.equals(buttons[i+8].getIcon())){
						    	  samenumber++;
						    	  markdown=true;
						      }
						      }
						      if(isUpBound(i)==false){
						      if(iconlabel.equals(buttons[i-8].getIcon())){
						    	  samenumber++;
						    	  markup=true;
						      }
						      }
						      if(samenumber>=3){
						    	  //if there have been three or more three same neighbors 
						    	  //we can delete them now in case there are no same labels in the next if(samenumber>=2)
						    	  //because we have save label, so this setlabel() will make no difference
						    	  if(markleft){
							    	  int left=i-1;
					    		      samenumber=DoubleSame(buttons,iconlabel,left,samenumber);
					    	    	  buttons[left].setIcon(null);
					    	    	  buttons[left].setRolloverIcon(null);
					    	    	  buttons[i].setIcon(iconlabel);
					    	    	  buttons[i].setRolloverIcon(rolloverlabel);
					    	    	  //in the DoubleSame function, the buttons[i].label will be 
					    	    	  //set " " and this will affect the following DoubleSame()
					    	    	  //make the number2 in DoubleSame() can never be more than 1
					    	    	  //but when there are 5 same buttons and the button[i] is in the middle
					    	    	  //it will make mistake
					    	    	    	  }
						    	  if(markright){
							    	  int right=i+1;
					    		      samenumber=DoubleSame(buttons,iconlabel,right,samenumber);
					    		      buttons[right].setIcon(null);
					    		      buttons[right].setRolloverIcon(null);
					    		      buttons[i].setIcon(iconlabel);
					    		      buttons[i].setRolloverIcon(rolloverlabel);
					    		      }
						    	  if(markdown){
							    	  int down=i+8;
							    	  samenumber=DoubleSame(buttons,iconlabel,down,samenumber);
							    	  buttons[down].setIcon(null);
							    	  buttons[down].setRolloverIcon(null);
							    	  buttons[i].setIcon(iconlabel);
							    	  buttons[i].setRolloverIcon(rolloverlabel);
							    	  }
						    	  if(markup){
							    	  int up=i-8;
							          samenumber=DoubleSame(buttons,iconlabel,up,samenumber);
							          buttons[up].setIcon(null);
							          buttons[up].setRolloverIcon(null);
							          buttons[i].setIcon(iconlabel);
							          buttons[i].setRolloverIcon(rolloverlabel);
						    	  }
						    	
						      }
						   else if(samenumber>=2){
							    	if(markright){
						    		      int right2=i+1;
						    		      samenumber=DoubleSame(buttons,iconlabel,right2,samenumber);
						    	  }
						    	if(markleft){
						    		 int left2=i-1;
					    		      samenumber=DoubleSame(buttons,iconlabel,left2,samenumber);
					   		    	  }
						    	if(markdown){
						    		int down2=i+8;
						    		 samenumber=DoubleSame(buttons,iconlabel,down2,samenumber);
						    	}
						    	if(markup){
						    		int up2=i-8;
						    		 samenumber=DoubleSame(buttons,iconlabel,up2,samenumber);
						    	}
						   	   			      }//there is no next same neighbor 
						      //because we go through all the labels and delete the same three(or more) after every operation
						      //it is impossible to find the next same neighbor of (the neighbor of (the neighbor of buttons[i] 
						      //or this neighbor and the neighbor of (the neighbor of buttons[i] and the neighbor of buttons[i]
						      //will be three same labels which should be deleted when we go through all the labels
						    if(samenumber>=3){
						    	 buttons[i].setIcon(null);
						    	  buttons[i].setRolloverIcon(null);
						  		 deleted=true;
						  	 }	
						  
							}//for(int i:places){
							
							return deleted;
							}
	///////////////////////////////////////////////////////////////////////////
	//play game1
	///////////////////////////////////////////////////////////////////////////
  public static String playGame1(JPanel panel2,JTextField step,JTextField score,JButton exit){
	  Object current=new Object();
	  if(returnmessage.equals("begin")){
			 exit.addActionListener(
					 new ActionListener(){
						 public void actionPerformed(ActionEvent ex){
							 returnmessage="exit";
							 synchronized(current) {
	      		      			  
	    		      				current.notify();
	    		      				
	    		      				
	    		      				}
							 
						 }
					 }
					 );
		  GAME1(panel2,step,score,exit,current);
	  }
	  synchronized(current) {
			  
		  try{
				 while(returnmessage.equals("begin")){
					 current.wait();
				 }
			  }catch(InterruptedException ex){
				  ex.printStackTrace();
			  }
				
				
				}
	  return returnmessage;
	  
  }




}


