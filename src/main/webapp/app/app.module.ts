import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { TkhipSharedModule } from 'app/shared/shared.module';
import { TkhipCoreModule } from 'app/core/core.module';
import { TkhipAppRoutingModule } from './app-routing.module';
import { TkhipHomeModule } from './home/home.module';
import { TkhipEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    TkhipSharedModule,
    TkhipCoreModule,
    TkhipHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    TkhipEntityModule,
    TkhipAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class TkhipAppModule {}
