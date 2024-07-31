import { Component } from '@angular/core';
import { AdminauthService } from '../adminauth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adlogin',
  templateUrl: './adlogin.component.html',
  styleUrl: './adlogin.component.css'
})
export class AdloginComponent {

  username2:string='';
  password2:string='';

  invalidLogin=false;

  constructor(private adminAuthService:AdminauthService,private router:Router){}

  checkLogin(){
    if(this.adminAuthService.authenticate(this.username2,this.password2)){
        this.router.navigate(['admin'])
        this.invalidLogin=false;
    }else{
      this.invalidLogin=true;
      alert("Wrong Credentials");
      this.router.navigate(['home'])
    }
  }
}
