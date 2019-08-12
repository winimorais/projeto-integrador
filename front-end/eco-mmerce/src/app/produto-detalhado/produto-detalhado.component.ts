import { Component, OnInit } from '@angular/core';
import {Produto} from '../produto';
import {TokenStorageService} from '../auth/token-storage.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ProdutoService} from '../produto.service';

@Component({
  selector: 'app-produto-detalhado',
  templateUrl: './produto-detalhado.component.html',
  styleUrls: ['./produto-detalhado.component.css']
})
export class ProdutoDetalhadoComponent implements OnInit {

  id: number;
  produto: Produto;

  constructor(private token: TokenStorageService,
              private route: ActivatedRoute,
              private router: Router,
              private produtoService: ProdutoService) { }

  ngOnInit() {
    this.produto = new Produto();

    this.id = this.route.snapshot.params.id;

    this.produtoService.getProduto(this.id)
      .subscribe(data => {
        console.log(data);
        this.produto = data;
      }, error => console.log(error));
  }
}
