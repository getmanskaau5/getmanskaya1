from config.config import cfg, logger
from custom_shape.custom_load_shapes import MyCustomLoadShape

if cfg.webtours_base.included:
    from user_classes.wt_base_scenario import WebToursBaseUserClass
    WebToursBaseUserClass.weight = cfg.webtours_base.weight

    logger.info('Imported WebToursBaseUserClass')


if cfg.webtours_cancel.included:
    from user_classes.wt_cancel_scenario import WebToursCancelUserClass
    WebToursCancelUserClass.weight = cfg.webtours_cancel.weight
    logger.info(f'Imported WebToursChancelUserClass with {WebToursCancelUserClass.weight}')






