CREATE TABLE ss7_config (
  global_title            VARCHAR(20) NOT NULL,
  destination_point_code  INTEGER NOT NULL,
  ip_address              VARCHAR(60) NOT NULL,
  port                    INTEGER NOT NULL,
  server_name             VARCHAR(60) NOT NULL,
  association_name        VARCHAR(60) NOT NULL,
  functionality           VARCHAR(20) NOT NULL,
  ip_channel_type         VARCHAR(20) NOT NULL,
  network_appearance      INTEGER NOT NULL,
  network_indicator       INTEGER NOT NULL,
  routing_context         INTEGER NOT NULL,
  service_indicator       INTEGER NOT NULL,
  ssn_msc                 INTEGER NOT NULL,
  ssn_gsmscf              INTEGER NOT NULL,
  ssn_hlr                 INTEGER NOT NULL,
  ssn_map                 INTEGER NOT NULL,
  ssn_vlr                 INTEGER NOT NULL,
  PRIMARY KEY (global_title)
);

insert into ss7_config (
              global_title,
              destination_point_code,
              ip_address,
              port,
              server_name,
              association_name,
              functionality,
              ip_channel_type,
              network_appearance,
              network_indicator,
              routing_context,
              service_indicator,
              ssn_msc,
              ssn_gsmscf,
              ssn_hlr,
              ssn_map,
              ssn_vlr
              )
values
('263730100066', 1232, '192.1.1.29',   9200,  'eBridgeUSSDGw', 'eBridgeUSSDGw', 'SGW', 'TCP', 1, 1, 60, 1, 8, 93, 6, 5, 7),
('263730100004', 9,    '10.10.25.140', 33910, '148MSC',        '148MSC',        'SGW', 'TCP', 1, 1, 60, 1, 8, 93, 6, 5, 7);


