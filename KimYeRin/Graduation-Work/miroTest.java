
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class miroTest {
	public static void main(String[] args) {
//		getMiro(3,"dd");
//		getMiro(4,"dd");
		getMiro(5,"dd");
	}
	
	public static List<List<Integer>> getMiro(int level, String word){
		int row;
		int col;
		int bombCount;
		//길 1
		//갈수없는 길(폭탄) 0 
		
		//	레벨 1 2는 4*4 에 폭탄 3개
		//	레벨 3 4는 5*5에 폭탄 4개
		//	레벨 5는 6*6에 폭탄 5개 
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		List<Map<String, Object>> bombStoreList = new ArrayList<>();
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
		
		
		if(level == 1 || level == 2){//폭탄 3개
			row = 4;
			col = 4;
			bombCount = 2; //배열은 0 인덱스부터 시작이므로. 2로 초기 세팅
			
			while(true){
				for(int i = 0 ; i< row; i++){
					for(int j = 0 ; j< col; j++){
						if(arr[new Random().nextInt(arr.length)] == 0){
							Map<String,Object> bombStore = new HashMap<>();
							bombStore.put("bombStore", i + "," + j);
							if(bombStoreList.size() == 0 || (i== 0 && j ==0)){
								bombStoreList.add(bombStore);
							}else{
								for(int z = 0; z < bombStoreList.size() ; z++){
									String chkBomStore = bombStoreList.get(z).get("bombStore").toString();
									if(!chkBomStore.equals(i + "," + j) || (i== 0 && j ==0)){
										bombCount --;
										bombStoreList.add(bombStore);
										if(bombCount == 0){
											break;
										}
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
			//폭탄 좌표 확인
			for(int i = 0; i<bombStoreList.size(); i++){
				System.out.println("i값..." + i);
				System.out.println(bombStoreList.get(i).get("bombStore").toString());
			}
		}else if(level == 3 || level == 4){//폭탄 4개
			row = 5;
			col = 5;
			bombCount = 3; //배열은 0 인덱스부터 시작이므로. 3으로 초기 세팅
			while(true){
				for(int i = 0 ; i< row; i++){
					for(int j = 0 ; j< col; j++){
						if(arr[new Random().nextInt(arr.length)] == 0){
							Map<String,Object> bombStore = new HashMap<>();
							bombStore.put("bombStore", i + "," + j);
							if(bombStoreList.size() == 0 || (i== 0 && j ==0)){
								bombStoreList.add(bombStore);
							}else{
								for(int z = 0; z < bombStoreList.size() ; z++){
									String chkBomStore = bombStoreList.get(z).get("bombStore").toString();
									if(!chkBomStore.equals(i + "," + j) || (i== 0 && j ==0)){
										bombCount --;
										bombStoreList.add(bombStore);
										if(bombCount == 0){
											break;
										}
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
			//폭탄 좌표 확인
			for(int i = 0; i<bombStoreList.size(); i++){
				System.out.println("i값..." + i);
				System.out.println(bombStoreList.get(i).get("bombStore").toString());
			}
		}else if(level == 5){//폭탄 5개
			row = 6;
			col = 6;
			bombCount = 4;  //배열은 0 인덱스부터 시작이므로. 4로 초기 세팅
			while(true){
				for(int i = 0 ; i< row; i++){
					for(int j = 0 ; j< col; j++){
						if(arr[new Random().nextInt(arr.length)] == 0){
							Map<String,Object> bombStore = new HashMap<>();
							bombStore.put("bombStore", i + "," + j);
							if(bombStoreList.size() == 0 || (i== 0 && j ==0)){
								bombStoreList.add(bombStore);
							}else{
								for(int z = 0; z < bombStoreList.size() ; z++){
									String chkBomStore = bombStoreList.get(z).get("bombStore").toString();
									if(!chkBomStore.equals(i + "," + j) || (i== 0 && j ==0)){
										bombCount --;
										bombStoreList.add(bombStore);
										if(bombCount == 0){
											break;
										}
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
			//폭탄 좌표 확인
			for(int i = 0; i<bombStoreList.size(); i++){
				System.out.println("i값..." + i);
				System.out.println(bombStoreList.get(i).get("bombStore").toString());
			}
		}
		return null;
	}
}
