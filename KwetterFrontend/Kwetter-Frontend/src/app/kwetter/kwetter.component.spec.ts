import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KwetterComponent } from './kwetter.component';

describe('KwetterComponent', () => {
  let component: KwetterComponent;
  let fixture: ComponentFixture<KwetterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KwetterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KwetterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
