package com.cpm.tkhip.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.cpm.tkhip.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.cpm.tkhip.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.cpm.tkhip.domain.User.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Authority.class.getName());
            createCache(cm, com.cpm.tkhip.domain.User.class.getName() + ".authorities");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".pdas");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".entidadVentas");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".brands");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".clientes");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".payments");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".eventos");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".pasarelas");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".servicios");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".invoices");
            createCache(cm, com.cpm.tkhip.domain.Empresa.class.getName() + ".condicionesVentas");
            createCache(cm, com.cpm.tkhip.domain.EntidadVenta.class.getName());
            createCache(cm, com.cpm.tkhip.domain.EntidadVenta.class.getName() + ".dispVendGrupos");
            createCache(cm, com.cpm.tkhip.domain.EntidadVenta.class.getName() + ".entidads");
            createCache(cm, com.cpm.tkhip.domain.Pda.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Pda.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.Pda.class.getName() + ".tickets");
            createCache(cm, com.cpm.tkhip.domain.TicketsBlock.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TicketsBlock.class.getName() + ".tipotickets");
            createCache(cm, com.cpm.tkhip.domain.CanalVenta.class.getName());
            createCache(cm, com.cpm.tkhip.domain.CanalVenta.class.getName() + ".dispVendGrupos");
            createCache(cm, com.cpm.tkhip.domain.TipoPreventa.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TipoPreventa.class.getName() + ".entidadVentas");
            createCache(cm, com.cpm.tkhip.domain.TipoPreventa.class.getName() + ".tipoTickets");
            createCache(cm, com.cpm.tkhip.domain.TipoEvento.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TipoEvento.class.getName() + ".eventos");
            createCache(cm, com.cpm.tkhip.domain.Ubicacion.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Ubicacion.class.getName() + ".eventos");
            createCache(cm, com.cpm.tkhip.domain.Estado.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Estado.class.getName() + ".eventos");
            createCache(cm, com.cpm.tkhip.domain.Estado.class.getName() + ".tipoTickets");
            createCache(cm, com.cpm.tkhip.domain.GestionBanner.class.getName());
            createCache(cm, com.cpm.tkhip.domain.GestionBanner.class.getName() + ".empresas");
            createCache(cm, com.cpm.tkhip.domain.DispVendGrupo.class.getName());
            createCache(cm, com.cpm.tkhip.domain.DispVendUser.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Brand.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Brand.class.getName() + ".eventos");
            createCache(cm, com.cpm.tkhip.domain.Cliente.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Cliente.class.getName() + ".invoices");
            createCache(cm, com.cpm.tkhip.domain.Country.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Country.class.getName() + ".empresas");
            createCache(cm, com.cpm.tkhip.domain.Country.class.getName() + ".clientes");
            createCache(cm, com.cpm.tkhip.domain.Payment.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Payment.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.Transaction.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".dispVendUsers");
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".eventoTranslations");
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".promocodes");
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".tipoTickets");
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".tickets");
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".vendedors");
            createCache(cm, com.cpm.tkhip.domain.Evento.class.getName() + ".brands");
            createCache(cm, com.cpm.tkhip.domain.EventoT.class.getName());
            createCache(cm, com.cpm.tkhip.domain.EventoApi.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TicketsApi.class.getName());
            createCache(cm, com.cpm.tkhip.domain.PromoCode.class.getName());
            createCache(cm, com.cpm.tkhip.domain.PromoCode.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.TicketDesign.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TicketDesign.class.getName() + ".eventos");
            createCache(cm, com.cpm.tkhip.domain.TicketDesign.class.getName() + ".empresas");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".dispVendGrupos");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".dispVendUsers");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".tipoTicketTranslations");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".tipoTicketTaxes");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".tickets");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".invoiceLines");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".ticketsBlocks");
            createCache(cm, com.cpm.tkhip.domain.TipoTicket.class.getName() + ".tipoPreventas");
            createCache(cm, com.cpm.tkhip.domain.TipoTicketT.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TipoTicketTax.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Pasarela.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Pasarela.class.getName() + ".payments");
            createCache(cm, com.cpm.tkhip.domain.LanguageCp.class.getName());
            createCache(cm, com.cpm.tkhip.domain.LanguageCp.class.getName() + ".eventoTS");
            createCache(cm, com.cpm.tkhip.domain.LanguageCp.class.getName() + ".tipoTicketTS");
            createCache(cm, com.cpm.tkhip.domain.LanguageCp.class.getName() + ".servicioTS");
            createCache(cm, com.cpm.tkhip.domain.LanguageCp.class.getName() + ".condicionesVentaTS");
            createCache(cm, com.cpm.tkhip.domain.DailyTicket.class.getName());
            createCache(cm, com.cpm.tkhip.domain.DailyTicket.class.getName() + ".tipoTickets");
            createCache(cm, com.cpm.tkhip.domain.Ticket.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Ticket.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.Servicio.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Servicio.class.getName() + ".promocodes");
            createCache(cm, com.cpm.tkhip.domain.Servicio.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.Servicio.class.getName() + ".servicioTS");
            createCache(cm, com.cpm.tkhip.domain.Servicio.class.getName() + ".invoiceLines");
            createCache(cm, com.cpm.tkhip.domain.TipoIva.class.getName());
            createCache(cm, com.cpm.tkhip.domain.TipoIva.class.getName() + ".tipoTicketTaxes");
            createCache(cm, com.cpm.tkhip.domain.TipoIva.class.getName() + ".servicios");
            createCache(cm, com.cpm.tkhip.domain.ServicioT.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Zona.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Invoice.class.getName());
            createCache(cm, com.cpm.tkhip.domain.Invoice.class.getName() + ".transactions");
            createCache(cm, com.cpm.tkhip.domain.Invoice.class.getName() + ".invoiceLines");
            createCache(cm, com.cpm.tkhip.domain.InvoiceLine.class.getName());
            createCache(cm, com.cpm.tkhip.domain.CondicionesVenta.class.getName());
            createCache(cm, com.cpm.tkhip.domain.CondicionesVenta.class.getName() + ".condicionesVentaTS");
            createCache(cm, com.cpm.tkhip.domain.CondicionesVentaT.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
