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
  currentPage = 1;
  itemsPerPage = 10;

  constructor(private firstService:FirstService ){}

  ngOnInit(): void {
    this.firstService.getAllLinks().subscribe(data => {
      this.links = data;
    });
  }

  get totalPages() {
    return Math.ceil(this.links.length / this.itemsPerPage);
}

paginatedLinks() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.links.slice(startIndex, startIndex + this.itemsPerPage);
}

nextPage() {
    if (this.currentPage < this.totalPages) {
        this.currentPage++;
    }
}

previousPage() {
    if (this.currentPage > 1) {
        this.currentPage--;
    }
}



}
