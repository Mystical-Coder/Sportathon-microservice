package com.hcl.elch.sportathon.admin.pojo;

import com.hcl.elch.sportathon.admin.entities.MatchDetails;
import com.hcl.elch.sportathon.admin.entities.MatchResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchHistoryPojo {
    private MatchDetails matchDetails;
    private MatchResult matchResult;
}
