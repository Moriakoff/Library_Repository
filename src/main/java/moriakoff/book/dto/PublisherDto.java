package moriakoff.book.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "name")
@Builder
public class PublisherDto {

    @NonNull
    String name;

    String Country;
}
