public class Util {
	
	public static Map<String ,Object> randomWordCut(String word, int cuttingCnt){
		Map<String,Object> result = new HashMap<>();
		
		String cuttingWord = word;
		List<Integer> cuttingWordIndex = new ArrayList<Integer>();
		
		while(true){
			Random random = new Random();
			int wordLength = word.length();
			int randomInt = random.nextInt(wordLength-1) + 0;
			int dupChk = 0;
			
			for(int j = 0; j<cuttingWordIndex.size(); j++){
				if(cuttingWordIndex.get(j) == randomInt){
					dupChk = 1;
				}
			}
			if(dupChk ==0){
				cuttingWord = cuttingWord.substring(0, randomInt) + "_" + cuttingWord.substring(randomInt+1);
				cuttingWordIndex.add(randomInt);
				cuttingCnt --;
			}
			if(cuttingCnt == 0){
				break;
			}
		}
		result.put("cuttingWord", cuttingWord);
		result.put("cuttingWordIndex", cuttingWordIndex);
	
		return result;
	}
}
