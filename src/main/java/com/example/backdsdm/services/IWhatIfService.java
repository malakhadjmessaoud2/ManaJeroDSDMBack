package com.example.backdsdm.services;

import com.example.backdsdm.entities.WhatIf;
import org.bson.types.ObjectId;

import java.util.List;

public interface IWhatIfService {
    WhatIf addWhatIf(WhatIf whatIf);

    WhatIf retrieveWhatIf(ObjectId id);

    List<WhatIf> retrieveWhatIfs();

    WhatIf updateWhatIf(ObjectId id, WhatIf existingWhatIf);

    void removeWhatIf(ObjectId id);
}
