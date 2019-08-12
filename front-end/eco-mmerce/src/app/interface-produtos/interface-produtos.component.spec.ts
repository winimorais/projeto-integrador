import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InterfaceProdutosComponent } from './interface-produtos.component';

describe('InterfaceProdutosComponent', () => {
  let component: InterfaceProdutosComponent;
  let fixture: ComponentFixture<InterfaceProdutosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InterfaceProdutosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterfaceProdutosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
