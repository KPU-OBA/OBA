
package miro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Miro {
	
	public static List<List<Integer>> getMiro(int level){
		List<List<Integer>> result = new ArrayList<>();
		
		if(level == 1 || level == 2){
			int row = 4;
			int col = 4;
			int bombCount = 3;
			
			List<Map<String,Object>> bombStoreList = getBombStoreList(row,col,bombCount);
			
			result = setMiro(row,col,bombStoreList);
		}else if(level == 3 || level == 4){
			int row = 5;
			int col = 5;
			int bombCount = 4;
			
			List<Map<String,Object>> bombStoreList = getBombStoreList(row,col,bombCount);
			
			result = setMiro(row,col,bombStoreList);
		}else if(level == 5){
			int row = 6;
			int col = 6;
			int bombCount = 5;
			
			List<Map<String,Object>> bombStoreList = getBombStoreList(row,col,bombCount);
			
			result = setMiro(row,col,bombStoreList);
		}
		
		return result;
	}
	
	public static List<Map<String, Object>> getBombStoreList(int row, int col, int bombCount){
		List<Map<String, Object>> result = new ArrayList<>();
		int arr[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1}; 
		//row = 4;
		//col = 4;
		//bombCount = 2; 
		
		while(true){
			for(int i = 0 ; i< row; i++){
				for(int j = 0 ; j< col; j++){
					int dupChk =0;
					if(arr[new Random().nextInt(arr.length)] == 0){
						Map<String,Object> bombStore = new HashMap<>();
						bombStore.put("bombStore", i + "," + j);
						if(result.size() == 0 && !(i== 0 && j ==0)){
							result.add(bombStore);
							bombCount --;
						}else{
							for(int z = 0; z < result.size() ; z++){
								String chkBomStore = result.get(z).get("bombStore").toString();
								if(!chkBomStore.equals(i + "," + j) && !(i== 0 && j ==0) && !(i == row-1 && j ==col-1)){
									bombStore.put("bombStore", i + "," + j);
									dupChk = 1;
									break;
								}
							}
							if(dupChk ==1){
								result.add(bombStore);
								bombCount --;
								if(bombCount == 0){
									break;
								}
							}
						}
					}
				}
			}
			if(bombCount == 0){
				break;
			}
		}
		
//		for(int i = 0; i<result.size(); i++){
//			System.out.println("i..." + i);
//			System.out.println(result.get(i).get("bombStore").toString());
//		}
		return result;
	}
	
	public static List<List<Integer>> setMiro(int row, int col, List<Map<String,Object>> bombStoreList){
		List<List<Integer>> result = new ArrayList<>();
		
		for(int i=0; i< row; i++){
			List<Integer> rowList = new ArrayList<>();
			for(int j = 0 ; j< col; j++){
				int bombChk = 0;
				for(int z = 0; z< bombStoreList.size(); z++){
					String bombStore = bombStoreList.get(z).get("bombStore").toString();
					if(bombStore.equals(i + "," + j)){
						bombChk = 1;
					}
				}
				if(bombChk == 1){
					rowList.add(0);
				}else{
					rowList.add(1);
				}
			}
			result.add(rowList);
		}
		
		return result;
	}
}
