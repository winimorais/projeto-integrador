import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { ContatoComponent } from './contato/contato.component';
import { CarrinhoComponent } from './carrinho/carrinho.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { ProdutoDetalhadoComponent } from './produto-detalhado/produto-detalhado.component';
import { SobreComponent } from './sobre/sobre.component';
import {httpInterceptorProviders} from './auth/auth-interceptor';
import { HeaderComponent } from './compartilhados/header/header.component';
import { FooterComponent } from './compartilhados/footer/footer.component';
import { ClienteComponent } from './cliente/cliente.component';
import { InterfaceProdutosComponent } from './interface-produtos/interface-produtos.component';
@NgModule({
  declarations: [
    AppComponent,
    CadastroComponent,
    HomeComponent,
    ContatoComponent,
    CarrinhoComponent,
    ProdutosComponent,
    ProdutoDetalhadoComponent,
    SobreComponent,
    HeaderComponent,
    FooterComponent,
    InterfaceProdutosComponent,
    ClienteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
