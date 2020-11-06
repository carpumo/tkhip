import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'empresa',
        loadChildren: () => import('./empresa/empresa.module').then(m => m.TkhipEmpresaModule),
      },
      {
        path: 'entidad-venta',
        loadChildren: () => import('./entidad-venta/entidad-venta.module').then(m => m.TkhipEntidadVentaModule),
      },
      {
        path: 'pda',
        loadChildren: () => import('./pda/pda.module').then(m => m.TkhipPdaModule),
      },
      {
        path: 'tickets-block',
        loadChildren: () => import('./tickets-block/tickets-block.module').then(m => m.TkhipTicketsBlockModule),
      },
      {
        path: 'canal-venta',
        loadChildren: () => import('./canal-venta/canal-venta.module').then(m => m.TkhipCanalVentaModule),
      },
      {
        path: 'tipo-preventa',
        loadChildren: () => import('./tipo-preventa/tipo-preventa.module').then(m => m.TkhipTipoPreventaModule),
      },
      {
        path: 'tipo-evento',
        loadChildren: () => import('./tipo-evento/tipo-evento.module').then(m => m.TkhipTipoEventoModule),
      },
      {
        path: 'ubicacion',
        loadChildren: () => import('./ubicacion/ubicacion.module').then(m => m.TkhipUbicacionModule),
      },
      {
        path: 'estado',
        loadChildren: () => import('./estado/estado.module').then(m => m.TkhipEstadoModule),
      },
      {
        path: 'gestion-banner',
        loadChildren: () => import('./gestion-banner/gestion-banner.module').then(m => m.TkhipGestionBannerModule),
      },
      {
        path: 'disp-vend-grupo',
        loadChildren: () => import('./disp-vend-grupo/disp-vend-grupo.module').then(m => m.TkhipDispVendGrupoModule),
      },
      {
        path: 'disp-vend-user',
        loadChildren: () => import('./disp-vend-user/disp-vend-user.module').then(m => m.TkhipDispVendUserModule),
      },
      {
        path: 'brand',
        loadChildren: () => import('./brand/brand.module').then(m => m.TkhipBrandModule),
      },
      {
        path: 'cliente',
        loadChildren: () => import('./cliente/cliente.module').then(m => m.TkhipClienteModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.TkhipCountryModule),
      },
      {
        path: 'payment',
        loadChildren: () => import('./payment/payment.module').then(m => m.TkhipPaymentModule),
      },
      {
        path: 'transaction',
        loadChildren: () => import('./transaction/transaction.module').then(m => m.TkhipTransactionModule),
      },
      {
        path: 'evento',
        loadChildren: () => import('./evento/evento.module').then(m => m.TkhipEventoModule),
      },
      {
        path: 'evento-t',
        loadChildren: () => import('./evento-t/evento-t.module').then(m => m.TkhipEventoTModule),
      },
      {
        path: 'evento-api',
        loadChildren: () => import('./evento-api/evento-api.module').then(m => m.TkhipEventoApiModule),
      },
      {
        path: 'tickets-api',
        loadChildren: () => import('./tickets-api/tickets-api.module').then(m => m.TkhipTicketsApiModule),
      },
      {
        path: 'promo-code',
        loadChildren: () => import('./promo-code/promo-code.module').then(m => m.TkhipPromoCodeModule),
      },
      {
        path: 'ticket-design',
        loadChildren: () => import('./ticket-design/ticket-design.module').then(m => m.TkhipTicketDesignModule),
      },
      {
        path: 'tipo-ticket',
        loadChildren: () => import('./tipo-ticket/tipo-ticket.module').then(m => m.TkhipTipoTicketModule),
      },
      {
        path: 'tipo-ticket-t',
        loadChildren: () => import('./tipo-ticket-t/tipo-ticket-t.module').then(m => m.TkhipTipoTicketTModule),
      },
      {
        path: 'tipo-ticket-tax',
        loadChildren: () => import('./tipo-ticket-tax/tipo-ticket-tax.module').then(m => m.TkhipTipoTicketTaxModule),
      },
      {
        path: 'pasarela',
        loadChildren: () => import('./pasarela/pasarela.module').then(m => m.TkhipPasarelaModule),
      },
      {
        path: 'language-cp',
        loadChildren: () => import('./language-cp/language-cp.module').then(m => m.TkhipLanguageCpModule),
      },
      {
        path: 'daily-ticket',
        loadChildren: () => import('./daily-ticket/daily-ticket.module').then(m => m.TkhipDailyTicketModule),
      },
      {
        path: 'ticket',
        loadChildren: () => import('./ticket/ticket.module').then(m => m.TkhipTicketModule),
      },
      {
        path: 'servicio',
        loadChildren: () => import('./servicio/servicio.module').then(m => m.TkhipServicioModule),
      },
      {
        path: 'tipo-iva',
        loadChildren: () => import('./tipo-iva/tipo-iva.module').then(m => m.TkhipTipoIvaModule),
      },
      {
        path: 'servicio-t',
        loadChildren: () => import('./servicio-t/servicio-t.module').then(m => m.TkhipServicioTModule),
      },
      {
        path: 'zona',
        loadChildren: () => import('./zona/zona.module').then(m => m.TkhipZonaModule),
      },
      {
        path: 'invoice',
        loadChildren: () => import('./invoice/invoice.module').then(m => m.TkhipInvoiceModule),
      },
      {
        path: 'invoice-line',
        loadChildren: () => import('./invoice-line/invoice-line.module').then(m => m.TkhipInvoiceLineModule),
      },
      {
        path: 'condiciones-venta',
        loadChildren: () => import('./condiciones-venta/condiciones-venta.module').then(m => m.TkhipCondicionesVentaModule),
      },
      {
        path: 'condiciones-venta-t',
        loadChildren: () => import('./condiciones-venta-t/condiciones-venta-t.module').then(m => m.TkhipCondicionesVentaTModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class TkhipEntityModule {}
