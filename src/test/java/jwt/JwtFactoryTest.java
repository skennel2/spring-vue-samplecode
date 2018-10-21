package jwt;

import org.almansa.app.domain.UserAccount;
import org.almansa.app.web.security.jwt.UserAccoutJwtFactory;
import org.junit.Test;

public class JwtFactoryTest {
	@Test
	public void jwtTokenGenegateTest() {
		UserAccount account = new UserAccount("skennel@naver.com", "12234");
		account.setId(new Long(10));

		UserAccoutJwtFactory factory = new UserAccoutJwtFactory();
		String token = factory.generateToken(account);
		System.out.println(token);
		
	}
}
