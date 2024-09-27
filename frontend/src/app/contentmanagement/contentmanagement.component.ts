import { Component,OnInit} from '@angular/core';
import { FirstService } from '../services/first.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-contentmanagement',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './contentmanagement.component.html',
  styleUrl: './contentmanagement.component.css'
})
export class ContentmanagementComponent implements OnInit {

  links:any[]=[];

  constructor(private firstService:FirstService ){}

  ngOnInit(): void {
    this.firstService.getAllLinks().subscribe(data => {
      this.links = data;
    });
  }



}
