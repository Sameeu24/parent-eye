import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class FirstService {

  private baseUrl="http://localhost:8100/content"

  constructor(private http:HttpClient){}


  getAllLinks():Observable<any>{

    return this.http.get(`${this.baseUrl}`)
  }
}
