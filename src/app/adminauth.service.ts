import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminauthService {

  constructor() { }

  authenticate(username2:string,password2:string){

    if(username2=='Swayam' && password2=='Swayam@007'){
      sessionStorage.setItem('username2',username2);
      return true
    }
    else{
      return false
    }
  }

  isUserLoggedIn(){
    console.log("User Is Logged In")
    let user = sessionStorage.getItem('username2');
    return !(user==null)
  }

  logout(){
    console.log("User Is Logged Out")
    sessionStorage.removeItem('username2');
  }
}
