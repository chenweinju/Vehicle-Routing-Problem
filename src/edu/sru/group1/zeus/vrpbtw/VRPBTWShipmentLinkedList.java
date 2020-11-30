/*
 

  Based off Dr. Thangiah template of Zeus

 This is the VRPBShipmentLinkedList,  this is where the VRPBShipmentLinkedList is created and where 
 the Selection heuristics are kept
 
 Group 5
 Nassir Weaver
 Francisco Maya
 Tyler Kimmel

 */


package edu.sru.group1.zeus.vrpbtw;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

//import edu.sru.thangiah.zeus.core.Depot;
import edu.sru.thangiah.zeus.core.ShipmentLinkedList;
import edu.sru.thangiah.zeus.core.Shipment;
import edu.sru.thangiah.zeus.core.ZeusProblemInfo;
//import edu.sru.group1.zeus.vrpb.*;

/**
*
* <p>VRPBShipmentLinkedList:</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: </p>
* @author Nassir Weaver, Francisco Maya, Tyler Kimmel (Sam R. Thangiah's VRP as reference/starting point)
* @version 1.0
*/
public class VRPBTWShipmentLinkedList extends ShipmentLinkedList implements java.io.Serializable, java.lang.Cloneable
{
	public VRPBTWShipmentLinkedList() 
	{
		setHead(new VRPBTWShipment()); //header node for head
		setTail(new VRPBTWShipment()); //tail node for tail
		linkHeadTail();			  //point head and tail to each other
		setNumShipments(0);
	}

	public void insertShipment(int num, float x, float y, int q, int d, int e,
			int comb, String type,
			int[] vComb, int[][] cuComb)
	{
		if (vComb.length <= ZeusProblemInfo.getMaxCombinations())
		{
			//create an instance of the Shipment
			VRPBTWShipment thisShip = new VRPBTWShipment(num, x, y, d, q, e, comb, type,
					vComb, cuComb);
			//add the instance to the linked list - in this case it is added at the end of the list
			//the total number of shipments is incremented in the insert
			insertLast(thisShip);
		}
		else 
		{
			System.out.println(
					"VRPShipmentLinkedList: Maximum number of combinations exceeded");
		}
	}
	//VRPB calls this insertShipment because it is the info coming in from the file
	public void insertShipment(String custType, int xCoord, int yCoord, int capacity,int shipmentIndex)
	{
		VRPBTWShipment thisShip = new VRPBTWShipment(custType, xCoord, yCoord, capacity,shipmentIndex); //creates shipment
		//System.out.println(custType);
		insertLast(thisShip); //and adds it to the linked list
	}
	
	public void insertShipment(String custType, int xCoord, int yCoord, int capacity,int shipmentIndex,double earl, double late,double service)
	{
		VRPBTWShipment thisShip = new VRPBTWShipment(custType, xCoord, yCoord, capacity,shipmentIndex,earl,late,service); //creates shipment
		//System.out.println(custType);
		insertLast(thisShip);
		
	}


	public VRPBTWShipment getVRPBTWHead() {
		return (VRPBTWShipment) getHead();
	}
	public VRPBTWShipment getVRPBTWTail() {
		return (VRPBTWShipment) getTail();
	}

	public VRPBTWShipment getNextInsertShipment(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipmentLL,
			VRPBTWShipment currShip) 
	{

		VRPBTWShipmentLinkedList selectShip = (VRPBTWShipmentLinkedList) ZeusProblemInfo.getSelectShipType();
		return selectShip.getSelectShipment(currDepotLL, currDepot, currShipmentLL,currShip);
	}
	public VRPBTWShipment getSelectShipment(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipmentLL,
			VRPBTWShipment currShip) 
	{
		return null;
	}
	public VRPBTWShipment getNextInsertShipmentLineHaul(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipmentLL,
			VRPBTWShipment currShip) 
	{

		VRPBTWShipmentLinkedList selectShip = (VRPBTWShipmentLinkedList) ZeusProblemInfo.getSelectShipType();
		return selectShip.getSelectShipmentLineHaul(currDepotLL, currDepot, currShipmentLL,currShip);
	}
	public VRPBTWShipment getSelectShipmentLineHaul(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipmentLL,
			VRPBTWShipment currShip) 
	{
		return null;
	} 
	public VRPBTWShipment getNextInsertShipmentBackHaul(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipmentLL,
			VRPBTWShipment currShip) 
	{

		VRPBTWShipmentLinkedList selectShip = (VRPBTWShipmentLinkedList) ZeusProblemInfo.getSelectShipType();
		return selectShip.getSelectShipmentBackHaul(currDepotLL, currDepot, currShipmentLL,currShip);
	}
	public VRPBTWShipment getSelectShipmentBackHaul(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipmentLL,
			VRPBTWShipment currShip) 
	{
		return null;
	} 
	public void printVRPBTWShipmentsToConsole() 
	{
		 //System.out.println(this.getNumShipments());
		
				Shipment ship = super.getHead().getNext();
				VRPBTWShipment vrpbtwShip;
				while (ship != getTail())
				{
					vrpbtwShip = (VRPBTWShipment)ship ;
					
					
					//if linehaul print the linehaul info
					if(vrpbtwShip.getCustomerType().equals("0"))
					{
					 System.out.println(vrpbtwShip.getIndex()  + " " + vrpbtwShip.getCustomerType() + " " + vrpbtwShip.getXCoord() + " " +
						  vrpbtwShip.getYCoord() + " " + vrpbtwShip.getDemand()+ " " + "LINEHAUL");
					}
					//if backhaul print the backhaul info 
					else if(vrpbtwShip.getCustomerType().equals("1"))
					{
					
					System.out.println(vrpbtwShip.getIndex()  + " " + vrpbtwShip.getCustomerType() + " " + vrpbtwShip.getXCoord() + " " +
					  vrpbtwShip.getYCoord() + " " + vrpbtwShip.getDemand() + " " + "BACKHAUL");
					}
					ship = ship.getNext();

				
				
				}
	}

	public void writeVRPBTWShipments(String fileString, String heuristic)
	{
		
		
		 HSSFWorkbook workbook2 = new HSSFWorkbook();
		  HSSFSheet sheet2 = workbook2.createSheet("Shipment data");
		 int rowNum = 0;
		  Row row2 = sheet2.createRow(rowNum);
		  Cell cell2 = row2.createCell(0);
		  
		  cell2.setCellValue("Shipment number");
		  cell2 = row2.createCell(1);
		  cell2.setCellValue("Truck Type");
		  cell2 = row2.createCell(2);
		  cell2.setCellValue("Demand (Negative = backhaul)");
		  cell2 = row2.createCell(3);
		  cell2.setCellValue("XCoord");
		  cell2=row2.createCell(4);
		  cell2.setCellValue("YCoord");
		  cell2=row2.createCell(5);
		  cell2.setCellValue("Earliest Time");
		  cell2=row2.createCell(6);
		  cell2.setCellValue("Latest Time");
		  cell2=row2.createCell(7);
		  cell2.setCellValue("Service Time");
		  cell2=row2.createCell(8);
		  cell2.setCellValue("Shipment Type");
		  
		  
		  //cell2.setCellValue(this.getNumShipments());
		  row2=sheet2.createRow(++rowNum);
		  
		  cell2=row2.createCell(0);
		  
		  
		  Shipment ship = super.getHead().getNext();
			VRPBTWShipment vrpbShip;
			
			while (ship != getTail())
			{
				
				vrpbShip = (VRPBTWShipment)ship;
				String custType = vrpbShip.getCustomerType();
				cell2.setCellValue(vrpbShip.getIndex());
				cell2=row2.createCell(1);
				cell2.setCellValue(vrpbShip.getTruckTypeNeeded());
				cell2=row2.createCell(2);
				cell2.setCellValue(vrpbShip.getDemand());
				cell2=row2.createCell(3);
				cell2.setCellValue(vrpbShip.getXCoord());
				cell2=row2.createCell(4);
				cell2.setCellValue(vrpbShip.getYCoord());
				cell2=row2.createCell(5);
				cell2.setCellValue(vrpbShip.getEarliestTime());
				cell2=row2.createCell(6);
				cell2.setCellValue(vrpbShip.getLatestTime());
				cell2=row2.createCell(7);
				cell2.setCellValue(vrpbShip.getServiceTime());
				cell2=row2.createCell(8);
				
				//System.out.println(vrpbShip.getCustomerType());
				
					if(vrpbShip.getCustomerType().equals("0"))
					{
						cell2.setCellValue("LINEHAUL");
					}
					else if (vrpbShip.getCustomerType().equals("1"))
					{
						cell2.setCellValue("BACKHAUL");
					}
					else
					{
						cell2.setCellValue("PROBLEM");
					}
				
				
				
				ship= ship.getNext();
				
				 row2=sheet2.createRow(++rowNum);
				  cell2=row2.createCell(0);
				
			}
		for(int i =0; i < 10; i++)
		{
			sheet2.autoSizeColumn(i);
		}
				  
		  try {
			    FileOutputStream shipmentsOutToFile = 
			            new FileOutputStream(new File(ZeusProblemInfo.getOutputPath() + fileString + "_shipments_" + heuristic + ".xls"));
			    workbook2.write(shipmentsOutToFile);
			    shipmentsOutToFile.close();
			   // System.out.println("Excel written successfully to " + ProblemInfo.outputPath+"readInData.xls");
			     
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}


		
	}




}
class ClosestEuclideanDistToDepot
extends VRPBTWShipmentLinkedList {
	public VRPBTWShipment getSelectShipment(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipLL,
			VRPBTWShipment currShip) {
		//currDepotLL is the depot linked list of the problem
		//currDepot is the depot under consideration
		//currShipLL is the set of avaialble shipments
		boolean isDiagnostic = false;
		//VRPShipment temp = (VRPShipment) getHead(); //point to the first shipment
		VRPBTWShipment temp = (VRPBTWShipment) currShipLL.getVRPBTWHead().getNext(); //point to the first shipment
		VRPBTWShipment foundShipment = null; //the shipment found with the criteria
		//double angle;
		//double foundAngle = 360; //initial value
		double distance;
		double foundDistance = 200; //initial distance
		double totalArrivalTime;
		double depotX, depotY;

		//Get the X and Y coordinate of the depot
		depotX = currDepot.getXCoord();
		depotY = currDepot.getYCoord();

		while (temp != currShipLL.getVRPBTWTail()) {
			if (isDiagnostic) {
				System.out.print("Shipment " + temp.getIndex() + " ");

				if ( ( (temp.getXCoord() - depotX) >= 0) &&
						( (temp.getYCoord() - depotX) >= 0)) {
					System.out.print("Quadrant I ");
				}
				else if ( ( (temp.getXCoord() - depotX) <= 0) &&
						( (temp.getYCoord() - depotY) >= 0)) {
					System.out.print("Quadrant II ");
				}
				else if ( ( (temp.getXCoord()) <= (0 - depotX)) &&
						( (temp.getYCoord() - depotY) <= 0)) {
					System.out.print("Quadrant III ");
				}
				else if ( ( (temp.getXCoord() - depotX) >= 0) &&
						( (temp.getYCoord() - depotY) <= 0)) {
					System.out.print("Quadrant VI ");
				}
				else {
					System.out.print("No Quadrant");
				}
			}
			if (temp.getIsAssigned()) 
			{
				if (isDiagnostic) 
				{
					System.out.println("has been assigned");
				}

				temp = (VRPBTWShipment) temp.getNext();

				continue;
			}
			/** @todo Associate the quadrant with the distance to get the correct shipment.
			 * Set up another insertion that takes the smallest angle and the smallest distance */
			distance = calcDist(depotX, temp.getXCoord(), depotY, temp.getYCoord());

			if (isDiagnostic) {
				System.out.println("  " + distance);
			}

			//check if this shipment should be tracked
			if (foundShipment == null) { //this is the first shipment being checked
				foundShipment = temp;
				foundDistance = distance;
			}
			else {
				if (distance < foundDistance) { //found an angle that is less
					foundShipment = temp;
					foundDistance = distance;
				}
			}
			temp = (VRPBTWShipment) temp.getNext();
		}
		//foundShipment.setArrivalTime(foundDistance);
		return foundShipment; //stub
	}
	public static String WhoAmI() 
	{
		return("Selection Type: Closest euclidean distance to depot");
	}

}


class SmallestPolarAngleToDepot
extends VRPBTWShipmentLinkedList {
	public VRPBTWShipment getSelectShipment(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipLL,
			VRPBTWShipment currShip) {
		//currDepotLL is the depot linked list of the problem
		//currDepot is the depot under consideration
		//currShipLL is the set of avaialble shipments
		boolean isDiagnostic = false;
		//VRPShipment temp = (VRPShipment) getHead(); //point to the first shipment
		VRPBTWShipment temp = (VRPBTWShipment) currShipLL.getVRPBTWHead().getNext(); //point to the first shipment

		VRPBTWShipment foundShipment = null;//the shipment found with the criteria
		VRPBTWShipmentLinkedList backhaulTempLL = new VRPBTWShipmentLinkedList();
		double angle = 0;
		double foundAngle = 360; //initial value
		double distance = 0;
		double foundDistance = 0; //initial distance
		double depotX, depotY;
		int type = 2;

		//Get the X and Y coordinate of the depot
		depotX = currDepot.getXCoord();
		depotY = currDepot.getYCoord();
		
		while (temp != currShipLL.getVRPBTWTail()) {
			//System.out.println(temp.getCustomerType());

			if (isDiagnostic) {
				System.out.println("Temp is "+temp);
				System.out.println("Tail is "+getTail());
				System.out.print("Shipment " + temp.getIndex() + " ");

				if ( ( (temp.getXCoord() - depotX) >= 0) &&
						( (temp.getYCoord() - depotX) >= 0)) {
					System.out.print("Quadrant I ");
				}
				else if ( ( (temp.getXCoord() - depotX) <= 0) &&
						( (temp.getYCoord() - depotY) >= 0)) {
					System.out.print("Quadrant II ");
				}
				else if ( ( (temp.getXCoord()) <= (0 - depotX)) &&
						( (temp.getYCoord() - depotY) <= 0)) {
					System.out.print("Quadrant III ");
				}
				else if ( ( (temp.getXCoord() - depotX) >= 0) &&
						( (temp.getYCoord() - depotY) <= 0)) {
					System.out.print("Quadrant VI ");
				}
				else {
					System.out.print("No Quadrant");
				}
			}
			
			//if the shipment is assigned, skip it
			if (temp.getIsAssigned()) {
				if (isDiagnostic) {
					System.out.println("has been assigned");
				}

				temp = (VRPBTWShipment) temp.getNext();

				continue;
			}
			distance = calcDist(depotX, temp.getXCoord(), depotY, temp.getYCoord());
			angle = calcPolarAngle(depotX, depotX, temp.getXCoord(),
					temp.getYCoord());

			if (isDiagnostic) {
				System.out.println("  " + angle);
			}

			//check if this shipment should be tracked
			if (foundShipment == null) { //this is the first shipment being checked
				foundShipment = temp;
				foundAngle = angle;
				foundDistance = distance;
			}
			else {
				if (angle < foundAngle) 
				{ //found an angle that is less
					
					if(currShip.getCustType().equals("0"))
					{
						foundShipment = temp;
						foundAngle = angle;
						foundDistance = distance;
					}
					else if(currShip.getCustType().equals("1"))
					{
						foundShipment = temp;
						foundAngle = angle;
						foundDistance = distance;
						backhaulTempLL.insertShipment(temp.getCustType(),(int)temp.getXCoord(),(int)temp.getYCoord(),temp.getDemand(),temp.getIndex());
					}
				}
			}
			temp =  (VRPBTWShipment) temp.getNext();
			return foundShipment;
		}
		temp = (VRPBTWShipment) backhaulTempLL.getHead();
		while(temp!=backhaulTempLL.getTail())
		{
			
			if (foundShipment == null) { //this is the first shipment being checked
				foundShipment = (VRPBTWShipment) temp;
				foundAngle = angle;
				foundDistance = distance;
			}
			else 
			{
				if (angle < foundAngle)
				{
					foundShipment = (VRPBTWShipment) temp;
					foundAngle = angle;
					foundDistance = distance;
				}
			temp = (VRPBTWShipment) temp.getNext();
			return foundShipment;

			}
		}
		//foundShipment.setArrivalTime(foundDistance);
		}

	//The WhoAmI methods gives the id of the assigned object
	//It is a static method so that it can be accessed without creating an object
	public static String WhoAmI() {
		return("Selection Type: Smallest polar angle to the depot");
	}
}


//Select the shipment with the smallest polar coordinate angle and the
//shortest distance to the depot
class SmallestPolarAngleShortestDistToDepot
extends VRPBTWShipmentLinkedList {
	public VRPBTWShipment getSelectShipment(VRPBTWDepotLinkedList currDepotLL,
			VRPBTWDepot currDepot,
			VRPBTWShipmentLinkedList currShipLL,
			VRPBTWShipment currShip) {
		//currDepotLL is the depot linked list of the problem
		//currDepot is the depot under consideration
		//currShipLL is the set of avaialble shipments
		boolean isDiagnostic = false;
		//VRPShipment temp = (VRPShipment) getHead(); //point to the first shipment
		VRPBTWShipment temp = (VRPBTWShipment)currShipLL.getVRPBTWHead().getNext(); //point to the first shipment
		VRPBTWShipment foundShipment = null; //the shipment found with the criteria
		double angle;
		double foundAngle = 360; //initial value
		double distance;
		double foundDistance = 200; //initial distance
		double depotX, depotY;
		double totalArrivalTime;
		int type = 2;

		//Get the X and Y coordinate of the depot
		depotX = currDepot.getXCoord();
		depotY = currDepot.getYCoord();

		while (temp != currShipLL.getVRPBTWTail()) {
			if (isDiagnostic) {
				System.out.print("Shipment " + temp.getIndex() + " ");

				if ( ( (temp.getXCoord() - depotX) >= 0) &&
						( (temp.getYCoord() - depotX) >= 0)) {
					System.out.print("Quadrant I ");
				}
				else if ( ( (temp.getXCoord() - depotX) <= 0) &&
						( (temp.getYCoord() - depotY) >= 0)) {
					System.out.print("Quadrant II ");
				}
				else if ( ( (temp.getXCoord()) <= (0 - depotX)) &&
						( (temp.getYCoord() - depotY) <= 0)) {
					System.out.print("Quadrant III ");
				}
				else if ( ( (temp.getXCoord() - depotX) >= 0) &&
						( (temp.getYCoord() - depotY) <= 0)) {
					System.out.print("Quadrant VI ");
				}
				else {
					System.out.print("No Quadrant");
				}
			}

			//if the shipment is assigned, skip it
			if (temp.getIsAssigned()) {
				if (isDiagnostic) {
					System.out.println("has been assigned");
				}

				temp = (VRPBTWShipment) temp.getNext();

				continue;
			}

			distance = calcDist(depotX, temp.getXCoord(), depotY, temp.getYCoord());
			angle = calcPolarAngle(depotX, depotX, temp.getXCoord(),
					temp.getYCoord());

			if (isDiagnostic) {
				System.out.println("  " + angle);
			}

			//check if this shipment should be tracked
			if (foundShipment == null) { //this is the first shipment being checked
				foundShipment = temp;
				foundAngle = angle;
				foundDistance = distance;
			}
			else {
				//if angle and distance are smaller than what had been found
				//if (angle <= foundAngle && distance <= foundDistance) {
				if (angle+ distance  <= foundAngle + foundDistance) {
					//if ((angle*.90)+ (distance * 0.1)  <= (foundAngle*0.9) + (foundDistance*0.1)) {
					foundShipment = temp;
					foundAngle = angle;
					foundDistance = distance;
				}
			}	
			temp = (VRPBTWShipment) temp.getNext();
		}
		//foundShipment.setArrivalTime(foundDistance);
		return foundShipment; //stub
	}
	
	//The WhoAmI methods gives the id of the assigned object
	//It is a static method so that it can be accessed without creating an object
	public static String WhoAmI() {
		return("Selection Type: Smallest polar angle to the depot");
	}

}
