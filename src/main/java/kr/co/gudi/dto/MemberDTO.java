package kr.co.gudi.dto;

// beans, VO, DTO 여러가지 이름으로 불린다.
// 본질은 데이터를 담고 있는 객체
public class MemberDTO {
	
	// 빈즈 규약 : private 으로 선언 할 것
	// 약속 : 컬럼명과 DTO 의 변수는 가급적이면 통일시켜 주자
	// 파라메터명 == DTO 변수명 == 컬럼명 이 가장 좋다.
	private String id;
	private String pw;
	private String name;
	private int age;
	private String gender;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
