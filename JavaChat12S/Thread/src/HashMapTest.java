import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapTest
{
	public static void main(String[] args)
	{
		// Map<키, 벨류> -> 키는 중복을 허용하지 않음.
		// 키가 중복인 경우 값을 변경
		Map<String, String> map = new HashMap<>();
		map.put("만화","너의 이름은");
		map.put("코미디","극한직업");
		map.put("영화","캘럭시가디언즈3");
		
		System.out.println(map);
		System.out.println();
		
		String key;
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			key = (String)it.next();
			System.out.println(map.get(key));
		}
		System.out.println();
		
		map.put("영화", "놀자..."); 	// 동일한 키는 값을 변경한다.
		System.out.println(map);
		System.out.println();
	}
}