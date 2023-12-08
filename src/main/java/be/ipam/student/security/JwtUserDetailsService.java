package be.ipam.student.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.ipam.student.model.StudentEntity;
import be.ipam.student.service.StudentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private static final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);
	
  @Autowired
  StudentService studentService;


  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	 log.info("loadUserByUsername "+username);
	  Optional<JwtUserDetails> user = Optional.empty();
	  //Ici normalement je récupère le user selon le paramètre login
	  Optional<StudentEntity> studentEntity = studentService.findByLogin(username);
	  log.info("FOUND " + studentEntity.get().getName());

	  if(studentEntity.isPresent()) {
		  StudentEntity se = studentEntity.get();
		  log.info("FOUND " + se.getStudentId());
		  List<String> rolelist = new ArrayList<String>();
		  se.getRoles().forEach(r -> rolelist.add("ROLE_"+r.getName().toUpperCase()));
		
		  //Ici je crée un user spring sur base de mon StudentEntity
		  user = Optional.of(new JwtUserDetails(se.getLogin(),se.getPassword(),rolelist));

	  }
	  
	  //if(pat.isPresent()) {
		//  user = Optional.of(new JwtUserDetails(pat.get().getId(),pat.get().getMail(),pat.get().getPw(),"ROLE_PATIENT")); 
	  //}
	  
	  //Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
      //  .filter(u -> u.getUsername().equals(username)).findFirst();
	  

	  if (user.isEmpty()) {
		  throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
	  }

	  //LOG
	  log.info("My pass is "+user.get().getPassword());
	  
	  return user.get();
  }

}