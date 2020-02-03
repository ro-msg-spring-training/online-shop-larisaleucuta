package ro.msg.learning.shop.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.Strategy.MostAbundantStrategy;
import ro.msg.learning.shop.Strategy.SingleLocationStrategy;

@Configuration
public class SelectLocationStrategy {

    @Value("${selected_strategy}")
    private String strategy;

    @Bean
    public LocationStrategy getDeliveryStrategy() {

        if (this.testEnum()) {
            StrategyEnum strategyValue = StrategyEnum.valueOf(strategy.toUpperCase());
            if (strategyValue == StrategyEnum.SINGLE_LOCATION) {
                return new SingleLocationStrategy();
            }
        }

        return new MostAbundantStrategy();
    }

    public boolean testEnum() {
        for (StrategyEnum strategyEnum : StrategyEnum.values())
            if (strategyEnum.name().equals(strategy.toUpperCase())) {
                return true;
            }
        return false;
    }
}


