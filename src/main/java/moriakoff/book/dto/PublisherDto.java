package moriakoff.book.dto;

import lombok.*;
import moriakoff.book.entity.Country;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "name")
@Builder
public class PublisherDto {

    @NonNull
    private String name;

    // FIXME: 15.12.2018
    private String country;
}
