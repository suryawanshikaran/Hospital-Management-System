import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DocauthService {

  constructor() { }

  authenticate(username:string,password:string){
    if(username=="Karan" && password=="Karan@007"){
      sessionStorage.setItem('username',username);
      
      return true;
    }
    else{
      alert("Wrong Credentials")
      
      return false;
    }
  }

  isUserLoggedIn(){
    console.log("User Is Logged In")
    let user = sessionStorage.getItem('username');
    return !(user==null)
  }

  logout(){
    console.log("User Is Logged Out")
    sessionStorage.removeItem('username');
  }
}
